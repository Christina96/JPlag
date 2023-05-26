package de.jplag.cpp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import de.jplag.AbstractParser;
import de.jplag.ParsingException;
import de.jplag.Token;

public class Scanner extends AbstractParser {
  private File currentFile;

  private List<Token> tokens;

  /**
   * Creates the parser.
   */
  public Scanner() {
    super();
  }

  public List<Token> scan(Set<File> files) throws ParsingException {
    tokens = new ArrayList<>();
    for (File file : files) {
      this.currentFile = file;
      logger.trace("Scanning file {}", currentFile);
      CPPScanner.scanFile(file, this);
      tokens.add(Token.fileEnd(currentFile));
    }
//    tokens = removeTokensForConsistency(tokens);
//    System.out.println("Tokens for:" + files);
//    System.out.println(tokens);
    return tokens;
  }

  public void add(CPPTokenType type, de.jplag.cpp.Token token) {
    int length = token.endColumn - token.beginColumn + 1;
    tokens.add(new Token(type, currentFile, token.beginLine, token.beginColumn, length));
  }

//  public List<Token> removeTokensForConsistency(List<Token> tokens) {
//    System.out.println("Token before remove:");
//    System.out.println(tokens);
//    ArrayList<Token> newTokens = new ArrayList<>();
//    for (Token token : tokens) {
//      if (!token.getType().equals(CPPTokenType.C_CHAR) &&
//            !token.getType().equals(CPPTokenType.C_DOUBLE) &&
//            !token.getType().equals(CPPTokenType.C_FLOAT) &&
//            !token.getType().equals(CPPTokenType.C_INT) &&
//            !token.getType().equals(CPPTokenType.C_LONG) &&
//            !token.getType().equals(CPPTokenType.C_NEW) &&
//            !token.getType().equals(CPPTokenType.C_PRIVATE) &&
//            !token.getType().equals(CPPTokenType.C_PROTECTED) &&
//            !token.getType().equals(CPPTokenType.C_PUBLIC) &&
//            !token.getType().equals(CPPTokenType.C_NULL)) {
//        newTokens.add(token);
//      }
//    }
//    return newTokens;
//  }
}
