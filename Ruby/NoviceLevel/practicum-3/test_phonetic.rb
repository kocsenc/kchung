require './phonetic'
require 'test/unit'

class TestPhonetic < Test::Unit::TestCase
	def setup
	     @phonetic = Phonetic.new
	end
	
	def test_01_to_phonetic_basic
	     assert_equal("ROMEO UNIFORM BRAVO YANKEE", @phonetic.to_phonetic("ruby"))
	end

	def test_02_to_phonetic_with_capitals
	     assert_equal("ROMEO INDIA TANGO", @phonetic.to_phonetic("RIT"),"Capitals not handled properly")
	end

	def test_03_to_phonetic_with_capitals_and_punct
	     assert_equal("ROMEO INDIA TANGO", @phonetic.to_phonetic("RIT!"),"Capitals and punctuation not handled properly")
	end

	def test_04_from_phonetic
	     assert_equal("ENGINEERING", @phonetic.from_phonetic("ECHO NOVEMBER GOLF INDIA NOVEMBER ECHO ECHO ROMEO INDIA NOVEMBER GOLF"))
	end

	def test_05_auto_from
	     assert_equal("RIT", @phonetic.auto_detect("ROMEO INDIA TANGO"),"Auto-detect wasn't 'from'")
	end

	def test_06_auto_to
	     assert_equal("ROMEO INDIA TANGO", @phonetic.auto_detect("RIT"),"Auto-detect wasn't 'to'")
	end

	def test_07_auto_should_be_from
	     assert_equal("INDIA NOVEMBER DELTA INDIA ALPHA NOVEMBER ALPHA", @phonetic.auto_detect("INDIANA"))
	end
end

