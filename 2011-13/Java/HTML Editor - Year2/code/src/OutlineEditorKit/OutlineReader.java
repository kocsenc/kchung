package TeamTwoHTMLEditor.OutlineEditorKit;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.text.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class OutlineReader {
    static OutlineReader instance = new OutlineReader();

    private OutlineReader() {

    }

    public static OutlineReader getInstance() {
        return instance;
    }

    public void read(InputStream is, Document d, int pos) throws IOException, BadLocationException {
        if (!(d instanceof OutlineDocument)) {
            return;
        }


        OutlineDocument doc = (OutlineDocument) d;
        doc.setUserChanges(false);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setCoalescing(true);
        dbf.setValidating(false);
        dbf.setIgnoringComments(false);
        dbf.setIgnoringElementContentWhitespace(false);


        try {
            //Using factory get an instance of document builder
            DocumentBuilder dbXML = dbf.newDocumentBuilder();

            //parse using builder to get DOM representation of the XML file
            org.w3c.dom.Document dom = dbXML.parse(is);

            ArrayList<DefaultStyledDocument.ElementSpec> specs = new ArrayList<DefaultStyledDocument.ElementSpec>();
            DefaultStyledDocument.ElementSpec spec = new DefaultStyledDocument.ElementSpec(new SimpleAttributeSet(), DefaultStyledDocument.ElementSpec.EndTagType);
            specs.add(spec);

//            printNode(dom, "");
            if (doc.getLength() == 0) {
                writeNode(doc, dom, pos, specs);
            } else {
                writeNode(doc, dom.getDocumentElement(), pos, specs);
            }

            DefaultStyledDocument.ElementSpec[] data = new DefaultStyledDocument.ElementSpec[specs.size()];
            specs.toArray(data);
            doc.insert(pos, data);

            doc.setUserChanges(true);
        } catch (SAXException pce) {
        } catch (IOException pce) {
        } catch (Exception blank) {

        }
    }

    public int writeNode(Document doc, Node node, int pos, ArrayList<DefaultStyledDocument.ElementSpec> specs) throws BadLocationException {
        SimpleAttributeSet tagAttrs = new SimpleAttributeSet();
        tagAttrs.addAttribute(AbstractDocument.ElementNameAttribute, OutlineDocument.TAG_ELEMENT);
        SimpleAttributeSet tagRowStartAttrs = new SimpleAttributeSet();
        tagRowStartAttrs.addAttribute(AbstractDocument.ElementNameAttribute, OutlineDocument.TAG_ROW_START_ELEMENT);
        SimpleAttributeSet tagRowEndAttrs = new SimpleAttributeSet();
        tagRowEndAttrs.addAttribute(AbstractDocument.ElementNameAttribute, OutlineDocument.TAG_ROW_END_ELEMENT);

        DefaultStyledDocument.ElementSpec spec;
        spec = new DefaultStyledDocument.ElementSpec(tagAttrs, DefaultStyledDocument.ElementSpec.StartTagType);
        specs.add(spec);
        spec = new DefaultStyledDocument.ElementSpec(tagRowStartAttrs, DefaultStyledDocument.ElementSpec.StartTagType);
        specs.add(spec);

        int offs = pos;
//        <
        spec = new DefaultStyledDocument.ElementSpec(OutlineDocument.BRACKET_ATTRIBUTES, DefaultStyledDocument.ElementSpec.ContentType, "<".toCharArray(), 0, 1);
        specs.add(spec);

//        tag name
        if (node instanceof org.w3c.dom.Document && doc.getLength() == 0) {
            org.w3c.dom.Document dd = (org.w3c.dom.Document) node;
            String nodeStr = "HTML";

            spec = new DefaultStyledDocument.ElementSpec(OutlineDocument.TAGNAME_ATTRIBUTES,
                    DefaultStyledDocument.ElementSpec.ContentType,
                    nodeStr.toCharArray(), 0, nodeStr.length());
        } else {
            spec = new DefaultStyledDocument.ElementSpec(OutlineDocument.TAGNAME_ATTRIBUTES,
                    DefaultStyledDocument.ElementSpec.ContentType,
                    node.getNodeName().toCharArray(), 0, node.getNodeName().length());
        }
        specs.add(spec);

        NamedNodeMap attrs = node.getAttributes();
        if (attrs != null && attrs.getLength() > 0) {
            for (int i = 0; i < attrs.getLength(); i++) {
                Node attr = attrs.item(i);
                String name = attr.getNodeName();
                String value = attr.getNodeValue();
//                " "
                spec = new DefaultStyledDocument.ElementSpec(OutlineDocument.BRACKET_ATTRIBUTES, DefaultStyledDocument.ElementSpec.ContentType, " ".toCharArray(), 0, 1);
                specs.add(spec);
//                attr name
                spec = new DefaultStyledDocument.ElementSpec(OutlineDocument.ATTRIBUTENAME_ATTRIBUTES, DefaultStyledDocument.ElementSpec.ContentType, name.toCharArray(), 0, name.length());
                specs.add(spec);
//                ="
                spec = new DefaultStyledDocument.ElementSpec(OutlineDocument.BRACKET_ATTRIBUTES, DefaultStyledDocument.ElementSpec.ContentType, "=\"".toCharArray(), 0, 2);
                specs.add(spec);
//                attr value
                spec = new DefaultStyledDocument.ElementSpec(OutlineDocument.ATTRIBUTEVALUE_ATTRIBUTES, DefaultStyledDocument.ElementSpec.ContentType, value.toCharArray(), 0, value.length());
                specs.add(spec);
//                "
                spec = new DefaultStyledDocument.ElementSpec(OutlineDocument.BRACKET_ATTRIBUTES, DefaultStyledDocument.ElementSpec.ContentType, "\"".toCharArray(), 0, 1);
                specs.add(spec);
            }
        }

        NodeList list = node.getChildNodes();
        if (list != null && list.getLength() > 0) {
//            >
            spec = new DefaultStyledDocument.ElementSpec(OutlineDocument.BRACKET_ATTRIBUTES, DefaultStyledDocument.ElementSpec.ContentType, ">\n".toCharArray(), 0, 2);
            specs.add(spec);
            spec = new DefaultStyledDocument.ElementSpec(tagRowStartAttrs, DefaultStyledDocument.ElementSpec.EndTagType);
            specs.add(spec);

            for (int i = 0; i < list.getLength(); i++) {
                Node n = list.item(i);
                if (n instanceof Element) {
                    Element child = (Element) n;
                    offs += writeNode(doc, child, offs, specs);
                } else if (n.getNodeType() == Node.COMMENT_NODE) {
                    //plain text
                    String str = n.getNodeValue();
                    int ii = str.indexOf("\n");
                    while (ii > 0) {
                        spec = new DefaultStyledDocument.ElementSpec(tagRowStartAttrs, DefaultStyledDocument.ElementSpec.StartTagType);
                        specs.add(spec);

                        String value = str.substring(0, ii);
                        spec = new DefaultStyledDocument.ElementSpec(OutlineDocument.COMMENT_ATTRIBUTES, DefaultStyledDocument.ElementSpec.ContentType, value.toCharArray(), 0, value.length());
                        specs.add(spec);
                        spec = new DefaultStyledDocument.ElementSpec(OutlineDocument.COMMENT_ATTRIBUTES, DefaultStyledDocument.ElementSpec.ContentType, "\n".toCharArray(), 0, 1);
                        specs.add(spec);

                        spec = new DefaultStyledDocument.ElementSpec(tagRowStartAttrs, DefaultStyledDocument.ElementSpec.EndTagType);
                        specs.add(spec);

                        str = str.substring(ii + 1);
                        ii = str.indexOf("\n");
                    }
                    spec = new DefaultStyledDocument.ElementSpec(tagRowStartAttrs, DefaultStyledDocument.ElementSpec.StartTagType);
                    specs.add(spec);

                    spec = new DefaultStyledDocument.ElementSpec(OutlineDocument.COMMENT_ATTRIBUTES, DefaultStyledDocument.ElementSpec.ContentType, str.toCharArray(), 0, str.length());
                    specs.add(spec);
                    spec = new DefaultStyledDocument.ElementSpec(OutlineDocument.COMMENT_ATTRIBUTES, DefaultStyledDocument.ElementSpec.ContentType, "\n".toCharArray(), 0, 1);
                    specs.add(spec);

                    spec = new DefaultStyledDocument.ElementSpec(tagRowStartAttrs, DefaultStyledDocument.ElementSpec.EndTagType);
                    specs.add(spec);
                } else {
                    //plain text
                    spec = new DefaultStyledDocument.ElementSpec(tagRowStartAttrs, DefaultStyledDocument.ElementSpec.StartTagType);
                    specs.add(spec);

                    spec = new DefaultStyledDocument.ElementSpec(OutlineDocument.PLAIN_ATTRIBUTES, DefaultStyledDocument.ElementSpec.ContentType, n.getNodeValue().toCharArray(), 0, n.getNodeValue().length());
                    specs.add(spec);
                    spec = new DefaultStyledDocument.ElementSpec(OutlineDocument.PLAIN_ATTRIBUTES, DefaultStyledDocument.ElementSpec.ContentType, "\n".toCharArray(), 0, 1);
                    specs.add(spec);

                    spec = new DefaultStyledDocument.ElementSpec(tagRowStartAttrs, DefaultStyledDocument.ElementSpec.EndTagType);
                    specs.add(spec);
                }
            }
            spec = new DefaultStyledDocument.ElementSpec(tagRowEndAttrs, DefaultStyledDocument.ElementSpec.StartTagType);
            specs.add(spec);
            if (node instanceof org.w3c.dom.Document) {
                spec = new DefaultStyledDocument.ElementSpec(OutlineDocument.TAGNAME_ATTRIBUTES, DefaultStyledDocument.ElementSpec.ContentType, " ".toCharArray(), 0, 1);
                specs.add(spec);
            } else {
                //            </
                spec = new DefaultStyledDocument.ElementSpec(OutlineDocument.BRACKET_ATTRIBUTES, DefaultStyledDocument.ElementSpec.ContentType, "</".toCharArray(), 0, 2);
                specs.add(spec);
                //            tag name
                spec = new DefaultStyledDocument.ElementSpec(OutlineDocument.TAGNAME_ATTRIBUTES, DefaultStyledDocument.ElementSpec.ContentType, node.getNodeName().toCharArray(), 0, node.getNodeName().length());
                specs.add(spec);
                //            />
                spec = new DefaultStyledDocument.ElementSpec(OutlineDocument.BRACKET_ATTRIBUTES, DefaultStyledDocument.ElementSpec.ContentType, ">\n".toCharArray(), 0, 2);
                specs.add(spec);
            }
            spec = new DefaultStyledDocument.ElementSpec(tagRowEndAttrs, DefaultStyledDocument.ElementSpec.EndTagType);
            specs.add(spec);
        } else {
//            />
            spec = new DefaultStyledDocument.ElementSpec(OutlineDocument.BRACKET_ATTRIBUTES, DefaultStyledDocument.ElementSpec.ContentType, "/>\n".toCharArray(), 0, 3);
            specs.add(spec);

            spec = new DefaultStyledDocument.ElementSpec(new SimpleAttributeSet(), DefaultStyledDocument.ElementSpec.EndTagType);
            specs.add(spec);
        }

        spec = new DefaultStyledDocument.ElementSpec(tagAttrs, DefaultStyledDocument.ElementSpec.EndTagType);
        specs.add(spec);

        return offs - pos;
    }

}
