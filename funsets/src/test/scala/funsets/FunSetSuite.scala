package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {


  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  test("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")
  }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  test("adding ints") {
    assert(1 + 2 === 3)
  }

  
  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }
  
  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   * 
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   * 
   *   val s1 = singletonSet(1)
   * 
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   * 
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   * 
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   * 
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(x) contains x") {
    
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
      assert(contains(s2, 2))
      assert(contains(s3, 3))
      assert(!contains(s1, 2))
    }
  }

  test("union contains all elements") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

    trait PopSets {
        def positiveSet = (x: Int) => x > 0
        def negativeSet = (x: Int) => x < 0
        def zeroSet = singletonSet(0)
        def evenSet = (x: Int) => x % 2 == 0
        def oddSet = (x: Int) => x % 2 == 1
    }

    test("test union") {
        new PopSets{
            assert(contains(union(positiveSet, negativeSet), -30))
            assert(!contains(union(positiveSet, zeroSet), -30))
            assert(contains(union(positiveSet, zeroSet), 0))
            assert(contains(union(positiveSet, evenSet), -4))
            assert(contains(union(union(positiveSet, oddSet), zeroSet), 0))
        }
    }
    
    test("test intersect") {
        new PopSets{
            assert(!contains(intersect(positiveSet, negativeSet), -30))
            assert(!contains(intersect(positiveSet, zeroSet), -30))
            assert(!contains(intersect(positiveSet, zeroSet), 0))
            assert(contains(intersect(positiveSet, evenSet), 4))
            assert(!contains(intersect(intersect(positiveSet, oddSet), zeroSet), 0))
        }
    }
  
    test("test diff") {
        new PopSets{
            assert(contains(diff(positiveSet, negativeSet), 30))
            assert(contains(diff(positiveSet, oddSet), 2))
            assert(!contains(diff(positiveSet, evenSet), 2))
            assert(!contains(diff(zeroSet, evenSet), 0))
            assert(contains(diff(diff(positiveSet, oddSet), zeroSet), 4))
        }
    }
    
    test("filter"){
        
        def s = (x: Int) => x >= 0 && x < 150
        def sp = filter(s, (x: Int) => x >= 100 && x < 200)
        // sp should be [100, 150[
        
        assert(!contains(sp, 90))
        assert(contains(sp, 100))
        assert(contains(sp, 120))
        assert(!contains(sp, 150))
        assert(!contains(sp, 200))
       
    }
  
    test("forall"){
        def s = (x: Int) => x > 900

        assert(!forall(s, x => x > 901))
        assert(forall(s, x => x > 800))
        
        def s2 = (x: Int) => x > 500 && x < 600
        assert(!forall(s2, x => x > 600))
    }
  
  
}
