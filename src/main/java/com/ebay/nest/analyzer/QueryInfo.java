package com.ebay.nest.analyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cascading.pipe.Pipe;
import cascading.tuple.Fields;

import com.ebay.nest.NestContext;
import com.ebay.nest.parser.ASTNode;

public class QueryInfo {

	private static final Log LOG = LogFactory.getLog(QueryInfo.class.getName());

	private NestContext ctx;
	private final String alias;
	private final boolean isSubQ;
	private String defaultAlias = null;
	private String updateAlias = null;
	private String insertTab = null;
	private String deleteAlias = null;
	private boolean isDistinct = false;
	private static final String DOT_ALIAS = "$";

	private List<String> insColumns = new ArrayList<String>();
	private int numSels = 0;
	private int numSelDi = 0;
	private Map<String, String> tabAliasToName = new HashMap<String, String>();
	private Map<String, Set<String>> aliasToCol = new HashMap<String, Set<String>>();
	private List<String> columns = new ArrayList<String>();
	private Map<String, QueryExpr> aliasToQueryExpr = new HashMap<String, QueryExpr>();
	private Map<String, Pipe> aliasToPipe = new HashMap<String, Pipe>();
	private List<String> aliases = new ArrayList<String>();
	private final Map<ASTNode, String> columnExprToAlias = new HashMap<ASTNode, String>();

	private ASTNode WhereExpr;// = new HashMap<String, ASTNode>();
	private final Map<String, String> clauseToDestName = new HashMap<String, String>();
	private final Map<String, ASTNode> clauseToSelNode = new LinkedHashMap<String, ASTNode>();
	private final HashMap<String, ASTNode> destToGroupby = new HashMap<String, ASTNode>();
	private final Map<String, ASTNode> destToHaving = new HashMap<String, ASTNode>();
	private final Map<String, ASTNode> destToQualify = new HashMap<String, ASTNode>();
	// for update
	private final Map<String, ASTNode> updToSetNode = new HashMap<String, ASTNode>();
	private QueryType queryType;

	private ASTNode joinNode;
	private JoinTree joinTree;

	private String id;
	private boolean isQuery;

	private QueryMetaData qbm;

	public JoinTree getJoinTree() {
		return joinTree;
	}

	public void setJoinTree(JoinTree joinTree) {
		this.joinTree = joinTree;
	}

	public void markDistinct() {
		this.isDistinct = true;
	}

	public boolean isDistinct() {
		return this.isDistinct;
	}

	public void setAliasCols(String alias, String col) {
		Set<String> cols = aliasToCol.get(alias);
		if (cols == null) {
			cols = new HashSet<String>();
			aliasToCol.put(alias, cols);
		}
		cols.add(col.toLowerCase());

	}

	public boolean containColsForAlias(String alias, String col) {
		Set<String> cols = aliasToCol.get(alias);
		if (cols == null)
			return false;
		if (cols.contains(col.toLowerCase()))
			return true;
		return false;
	}

	public String[] guessAliasForCol(String col) {
		List<String> aliases = new ArrayList<String>();
		for (String alias : aliasToCol.keySet()) {
			Set<String> cols = aliasToCol.get(alias);
			if (cols == null) {
				continue;
			}
			if (cols.contains(col.toLowerCase())) {
				aliases.add(alias);
			}
		}
		return aliases.toArray(new String[aliases.size()]);
	}

	public void setQueryType(QueryType qt) {
		queryType = qt;
	}

	public QueryType getQueryType() {

		return queryType;
	}

	public void setUpdSetNode(String upd, ASTNode node) {
		updToSetNode.put(upd, node);
	}

	public ASTNode getNodeForSetUpd(String expr) {
		return updToSetNode.get(expr);
	}

	public Map<String, ASTNode> getAllUpdToSetNode() {
		return updToSetNode;
	}

	public QueryInfo(NestContext ctx, String outer_id, String alias, boolean isSubQ) {
		if (alias != null) {
			alias = alias.toLowerCase();
		}
		this.isSubQ = isSubQ;
		this.alias = alias;
		qbm = new QueryMetaData();
		id = getAppendedAliasFromId(outer_id, alias);
		this.ctx = ctx;
	}

	public void setContext(NestContext ctx) {
		this.ctx = ctx;
	}

	public void addCol(String alias) {
		if (!columns.contains(alias.toLowerCase())) {
			columns.add(alias.toLowerCase());
		}
	}

	public List<String> getCol() {
		return columns;
	}

	public boolean exitsColumn(String column) {

		if (columns.contains(column.toLowerCase())) {
			return true;

		}
		return false;
	}

	public Class<?> getTypeForAliasColumn(String alias, String column) {
		Fields fields = ctx.getFieldsForPipe(getPipeForAlias(alias));
		return (Class<?>) fields.getType(alias + DOT_ALIAS + column);
	}

	public void addInsertCol(String alias) {
		if (!insColumns.contains(alias.toLowerCase())) {
			insColumns.add(alias.toLowerCase());
		}
	}

	public List<String> getInsertCol() {
		return insColumns;
	}

	public boolean exitsInsertColumn(String column) {

		if (insColumns.contains(column.toLowerCase())) {
			return true;

		}
		return false;
	}

	public synchronized String getDefaultAlias() {
		return defaultAlias;
	}

	public synchronized String setDefaultAlias(String alias) {
		if (this.defaultAlias == null) {
			this.defaultAlias = alias;
		}

		return this.defaultAlias;
	}

	public String getUpdateAlias() {
		return updateAlias;
	}

	public String setUpdateAlias(String alias) {
		if (this.updateAlias == null) {
			this.updateAlias = alias;
		}

		return this.updateAlias;
	}

	public String getDeleteAlias() {
		return deleteAlias;
	}

	public String setDeleteAlias(String alias) {
		if (this.deleteAlias == null) {
			this.deleteAlias = alias;
		}

		return this.deleteAlias;
	}

	public String getInsertTab() {
		return insertTab;
	}

	public String setInsertTab(String alias) {
		if (this.insertTab == null) {
			this.insertTab = alias;
		}

		return this.insertTab;
	}

	public ASTNode getJoinAST() {
		return joinNode;
	}

	public void setJoinAST(ASTNode joinExpr) {
		this.joinNode = joinExpr;
	}

	public boolean getIsSubQ() {
		return isSubQ;
	}

	public String getAlias() {
		return alias;
	}

	public void setWhrExprForClause(ASTNode ast) {
		WhereExpr = ast;
	}

	public ASTNode getWhereExpr() {
		return WhereExpr;
	}

	public void setExprToColumnAlias(ASTNode expr, String alias) {
		columnExprToAlias.put(expr, alias);
	}

	public String getExprToColumnAlias(ASTNode expr) {
		return columnExprToAlias.get(expr);
	}

	public Map<ASTNode, String> getAllExprToColumnAlias() {
		return columnExprToAlias;
	}

	public boolean hasExprToColumnAlias(ASTNode expr) {
		return columnExprToAlias.containsKey(expr);
	}

	public void setDestForClause(String clause, String dest) {
		clauseToDestName.put(clause, dest);
	}

	public Set<String> getClauseNamesForDest() {
		return clauseToDestName.keySet();
	}

	public String getDestForClause(String clause) {
		return clauseToDestName.get(clause);
	}

	public Set<String> getClauseNames() {
		return clauseToDestName.keySet();
	}

	public void setSelExprForClause(String clause, ASTNode ast) {
		clauseToSelNode.put(clause, ast);
	}

	public ASTNode getSelForClause(String clause) {
		return clauseToSelNode.get(clause);
	}

	public void print(String msg) {
		LOG.info(msg + "alias=" + getAlias());
		for (String alias : getSubqAliases()) {
			QueryExpr qbexpr = getSubqForAlias(alias);
			LOG.info(msg + "start subquery " + alias);
			qbexpr.print(msg + " ");
			LOG.info(msg + "end subquery " + alias);
		}
	}

	public void setAliasPipe(String alias, Pipe pipe) {
		aliasToPipe.put(alias.toLowerCase(), pipe);
	}

	public Pipe getPipeForAlias(String alias) {
		return aliasToPipe.get(alias.toLowerCase());
	}

	private String getAppendedAliasFromId(String outer_id, String alias) {
		if (alias != null) {
			return (outer_id == null ? ":" + alias : outer_id + ":" + alias);
		}

		return (outer_id == null ? "" : outer_id);
	}

	public void countSelDi() {
		numSelDi++;
	}

	public void countSel() {
		numSels++;
	}

	public boolean exists(String alias) {
		alias = alias.toLowerCase();
		if (tabAliasToName.get(alias) != null || aliasToQueryExpr.get(alias) != null) {
			return true;
		}

		return false;
	}

	public void setTabAlias(String alias, String tabName) {
		tabAliasToName.put(alias.toLowerCase(), tabName);
	}

	public void setSubqAlias(String alias, QueryExpr qbexpr) {
		aliasToQueryExpr.put(alias.toLowerCase(), qbexpr);
	}

	public void addAlias(String alias) {
		if (!aliases.contains(alias.toLowerCase())) {
			aliases.add(alias.toLowerCase());
		}
	}

	public List<String> getAliases() {
		return aliases;
	}

	public String getId() {
		return id;
	}

	public int getNumSelDi() {
		return numSelDi;
	}

	public int getNumSels() {
		return numSels;
	}

	public Set<String> getSubqAliases() {
		return aliasToQueryExpr.keySet();
	}

	public Set<String> getTabAliases() {
		return tabAliasToName.keySet();
	}

	public QueryExpr getSubqForAlias(String alias) {
		return aliasToQueryExpr.get(alias.toLowerCase());
	}

	public String getTabNameForAlias(String alias) {
		return tabAliasToName.get(alias.toLowerCase());
	}

	public void rewriteViewToSubq(String alias, String viewName, QueryExpr qbexpr) {
		alias = alias.toLowerCase();
		String tableName = tabAliasToName.remove(alias);
		assert (viewName.equals(tableName));
		aliasToQueryExpr.put(alias, qbexpr);
	}

	public void setIsQuery(boolean isQuery) {
		this.isQuery = isQuery;
	}

	public boolean getIsQuery() {
		return isQuery;
	}

	public void setGroupByExprForClause(String clause, ASTNode ast) {
		destToGroupby.put(clause, ast);
	}

	public HashMap<String, ASTNode> getDestToGroupBy() {
		return destToGroupby;
	}

	public ASTNode getGroupByForClause(String clause) {
		return destToGroupby.get(clause);
	}

	public void setHavingExprForClause(String clause, ASTNode ast) {
		destToHaving.put(clause, ast);
	}

	public ASTNode getHavingForClause(String clause) {
		return destToHaving.get(clause);
	}

	public void setQualifyExprForClause(String clause, ASTNode ast) {
		destToQualify.put(clause, ast);
	}

	public ASTNode getQualifyExprForClause(String clause) {
		return destToQualify.get(clause);
	}

	public ASTNode getHints() {
		return null;
	}

	public QueryMetaData getMetaData() {
		return qbm;
	}

}
