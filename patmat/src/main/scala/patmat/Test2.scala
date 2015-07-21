package patmat

object Test2 extends App{

    val foxCodeTree = Huffman.createCodeTree("the quick brown fox jumps over the lazy dog".toList)

    val text = "in the age of computers this pangram is commonly used to display font samples and for testing computer keyboards in cryptography it is commonly used as a test vector for hash and encryption algorithms to verify their implementation as well as to ensure alphabetic character set compatibility microsoft word has a command to autotype the sentence in versions up to word"              

    val encoded = Huffman.encode(foxCodeTree)(text.toList)
    val qencoded = Huffman.quickEncode(foxCodeTree)(text.toList)

    val orig = Huffman.decode(foxCodeTree, encoded).mkString
    val qorig = Huffman.decode(foxCodeTree, qencoded).mkString
    
    assert(orig == text)
    assert(qorig == text)
    println(orig)
    println(encoded)
    
    // -------------------------
    
    for (i <- 'a' to 'z'){
        println(i + " >> " + Huffman.codeBits(Huffman.convert(foxCodeTree))(i))
    }
}