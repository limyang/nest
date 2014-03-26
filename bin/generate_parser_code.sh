#!/bin/bash
BASE=$(cd "$(dirname "$0")/../";pwd)

export CLASSPATH=$BASE/lib/antlr/antlr-3.5-complete.jar:.

antlr3="java -jar $BASE/lib/antlr/antlr-3.5-complete.jar"

(cd $BASE/src/main/java/com/ebay/nest/parser/; $antlr3 SQLLexer.g)
(cd $BASE/src/main/java/com/ebay/nest/parser/; $antlr3 SQLParser.g)