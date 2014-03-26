package com.ebay.nest.parser;


import org.apache.hadoop.io.Text;

/**
 * General collection of helper functions.
 *
 */
public final class ParserUtil {

  public static final char QUOTE = '"';
  public static final char COLON = ':';
  public static final String LBRACKET = "[";
  public static final String RBRACKET = "]";
  public static final String LBRACE = "{";
  public static final String RBRACE = "}";
  public static final String LINE_SEP = System.getProperty("line.separator");

  public static String escapeString(String str) {
    int length = str.length();
    StringBuilder escape = new StringBuilder(length + 16);

    for (int i = 0; i < length; ++i) {
      char c = str.charAt(i);
      switch (c) {
      case '"':
      case '\\':
        escape.append('\\');
        escape.append(c);
        break;
      case '\b':
        escape.append('\\');
        escape.append('b');
        break;
      case '\f':
        escape.append('\\');
        escape.append('f');
        break;
      case '\n':
        escape.append('\\');
        escape.append('n');
        break;
      case '\r':
        escape.append('\\');
        escape.append('r');
        break;
      case '\t':
        escape.append('\\');
        escape.append('t');
        break;
      default:
        // Control characeters! According to JSON RFC u0020
        if (c < ' ') {
          String hex = Integer.toHexString(c);
          escape.append('\\');
          escape.append('u');
          for (int j = 4; j > hex.length(); --j) {
            escape.append('0');
          }
          escape.append(hex);
        } else {
          escape.append(c);
        }
        break;
      }
    }
    return (escape.toString());
  }

  static final byte[] escapeEscapeBytes = "\\\\".getBytes();;
  static final byte[] escapeUnescapeBytes = "\\".getBytes();
  static final byte[] newLineEscapeBytes = "\\n".getBytes();;
  static final byte[] newLineUnescapeBytes = "\n".getBytes();
  static final byte[] carriageReturnEscapeBytes = "\\r".getBytes();;
  static final byte[] carriageReturnUnescapeBytes = "\r".getBytes();
  static final byte[] tabEscapeBytes = "\\t".getBytes();;
  static final byte[] tabUnescapeBytes = "\t".getBytes();
  static final byte[] ctrlABytes = "\u0001".getBytes();

  public static Text escapeText(Text text) {
    int length = text.getLength();
    byte[] textBytes = text.getBytes();

    Text escape = new Text(text);
    escape.clear();

    for (int i = 0; i < length; ++i) {
      int c = text.charAt(i);
      byte[] escaped;
      int start;
      int len;

      switch (c) {

      case '\\':
        escaped = escapeEscapeBytes;
        start = 0;
        len = escaped.length;
        break;

      case '\n':
        escaped = newLineEscapeBytes;
        start = 0;
        len = escaped.length;
        break;

      case '\r':
        escaped = carriageReturnEscapeBytes;
        start = 0;
        len = escaped.length;
        break;

      case '\t':
        escaped = tabEscapeBytes;
        start = 0;
        len = escaped.length;
        break;

      case '\u0001':
        escaped = tabUnescapeBytes;
        start = 0;
        len = escaped.length;
        break;

      default:
        escaped = textBytes;
        start = i;
        len = 1;
        break;
      }

      escape.append(escaped, start, len);

    }
    return escape;
  }

  public static int unescapeText(Text text) {
    Text escape = new Text(text);
    text.clear();

    int length = escape.getLength();
    byte[] textBytes = escape.getBytes();

    boolean hadSlash = false;
    for (int i = 0; i < length; ++i) {
      int c = escape.charAt(i);
      switch (c) {
      case '\\':
        if (hadSlash) {
          text.append(textBytes, i, 1);
          hadSlash = false;
        }
        else {
          hadSlash = true;
        }
        break;
      case 'n':
        if (hadSlash) {
          byte[] newLine = newLineUnescapeBytes;
          text.append(newLine, 0, newLine.length);
        }
        else {
          text.append(textBytes, i, 1);
        }
        hadSlash = false;
        break;
      case 'r':
        if (hadSlash) {
          byte[] carriageReturn = carriageReturnUnescapeBytes;
          text.append(carriageReturn, 0, carriageReturn.length);
        }
        else {
          text.append(textBytes, i, 1);
        }
        hadSlash = false;
        break;

      case 't':
        if (hadSlash) {
          byte[] tab = tabUnescapeBytes;
          text.append(tab, 0, tab.length);
        }
        else {
          text.append(textBytes, i, 1);
        }
        hadSlash = false;
        break;

      case '\t':
        if (hadSlash) {
          text.append(textBytes, i-1, 1);
          hadSlash = false;
        }

        byte[] ctrlA = ctrlABytes;
        text.append(ctrlA, 0, ctrlA.length);
        break;

      default:
        if (hadSlash) {
          text.append(textBytes, i-1, 1);
          hadSlash = false;
        }

        text.append(textBytes, i, 1);
        break;
      }
    }
    return text.getLength();
  }

  public static String lightEscapeString(String str) {
    int length = str.length();
    StringBuilder escape = new StringBuilder(length + 16);

    for (int i = 0; i < length; ++i) {
      char c = str.charAt(i);
      switch (c) {
      case '\n':
        escape.append('\\');
        escape.append('n');
        break;
      case '\r':
        escape.append('\\');
        escape.append('r');
        break;
      case '\t':
        escape.append('\\');
        escape.append('t');
        break;
      default:
        escape.append(c);
        break;
      }
    }
    return (escape.toString());
  }

  /**
   * Regenerate an identifier as part of unparsing it back to SQL text.
   */
  public static String unparseIdentifier(String identifier) {
    // In the future, if we support arbitrary characters in
    // identifiers, then we'll need to escape any backticks
    // in identifier by doubling them up.
    return "`" + identifier + "`";
  }

}
