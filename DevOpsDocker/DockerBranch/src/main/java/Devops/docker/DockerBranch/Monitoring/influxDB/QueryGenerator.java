package Devops.docker.DockerBranch.Monitoring.influxDB;

import java.util.ArrayList;

/**
 * 针对查询语句的 生成类
 * 
 * 
 * */
public class QueryGenerator {
	
	private String query; //sql查询语句
	
	private ArrayList<String> displayColumn;
	
	private ArrayList<String> TableName;
	
	private ArrayList<Condition> Condition;
	
	private ArrayList<String> GroupByColumnName;
	
	private ArrayList<String> ConnectionCondition;
	
	private String OrderBy;
	
	private int Limit;

	public QueryGenerator(String query, ArrayList<String> displayColumn, ArrayList<String> tableName,
			ArrayList<Devops.docker.DockerBranch.Monitoring.influxDB.Condition> condition,
			ArrayList<String> groupByColumnName, ArrayList<String> connectionCondition, String orderBy, int limit) {
		super();
		this.query = query;
		this.displayColumn = displayColumn;
		TableName = tableName;
		Condition = condition;
		GroupByColumnName = groupByColumnName;
		ConnectionCondition = connectionCondition;
		OrderBy = orderBy;
		Limit = limit;
	}


	public QueryGenerator() {
		super();
		// TODO Auto-generated constructor stub
		query = "";
		displayColumn = new ArrayList<>();
		TableName = new ArrayList<>();
		Condition = new ArrayList<>();
		GroupByColumnName = new ArrayList<>();
		ConnectionCondition = new ArrayList<>();
		OrderBy = "";
		Limit = -1;
	}


	/**
	 * 添加一列 需要显示的列
	 * @param String displayColumn 列名
	 * @return QueryGenerator 返回一个新的QueryGenerator，以便级联调用
	 * */
	public QueryGenerator addDisplayColumn(String displayColumn) {
		this.displayColumn.add(displayColumn);
		return new QueryGenerator(this.query, this.displayColumn, this.TableName, this.Condition,
				this.GroupByColumnName,this.ConnectionCondition,this.OrderBy,this.Limit);
	}
	
	/**
	 * 添加一列 需要显示的列 该列拥有别名
	 * @param String displayColumn 列名
	 * @param String alias 别名
	 * @return QueryGenerator 返回一个新的QueryGenerator，以便级联调用
	 * */
	public QueryGenerator addDisplayColumn(String displayColumn,String alias) {
		this.displayColumn.add(displayColumn + " as " + alias);
		return new QueryGenerator(this.query, this.displayColumn, this.TableName, this.Condition,
				this.GroupByColumnName,this.ConnectionCondition,this.OrderBy,this.Limit);
	}
	
	/**
	 * 添加一列 需要查询的表
	 * @param String tableName 表名
	 * @return QueryGenerator 返回一个新的QueryGenerator，以便级联调用
	 * */
	public QueryGenerator addTableName(String tableName) {
		this.TableName.add(tableName);
		return new QueryGenerator(this.query, this.displayColumn, this.TableName, this.Condition,
				this.GroupByColumnName,this.ConnectionCondition,this.OrderBy,this.Limit);
	}
	
	/**
	 * 添加一列 需要查询的表 该表拥有别名
	 * @param String tableName 列名
	 * @param String alias 别名
	 * @return QueryGenerator 返回一个新的QueryGenerator，以便级联调用
	 * */
	public QueryGenerator addTableName(String tableName,String alias) {
		this.TableName.add(tableName + " as " + alias);
		return new QueryGenerator(this.query, this.displayColumn, this.TableName, this.Condition,
				this.GroupByColumnName,this.ConnectionCondition,this.OrderBy,this.Limit);
	}
	
	/**
	 * 添加一个查询条件
	 * @param Condition con 查询条件
	 * @return QueryGenerator 返回一个新的QueryGenerator，以便级联调用
	 * */
	public QueryGenerator addCondition(Condition con) {
		this.Condition.add(con);
		return new QueryGenerator(this.query, this.displayColumn, this.TableName, this.Condition,
				this.GroupByColumnName,this.ConnectionCondition,this.OrderBy,this.Limit);
	}
	
	/**
	 * 添加一个分组列
	 * @param String Column 分组列名
	 * @return QueryGenerator 返回一个新的QueryGenerator，以便级联调用
	 * */
	public QueryGenerator addGroupByColumn(String Column) {
		this.GroupByColumnName.add(Column);
		return new QueryGenerator(this.query, this.displayColumn, this.TableName, this.Condition,
				this.GroupByColumnName,this.ConnectionCondition,this.OrderBy,this.Limit);
	}
	
	/**
	 * 设置排序
	 * @param String orderByName 选择排序的列
	 * @param String order 升序或者降序(DESC或者ASC)
	 * @return QueryGenerator 返回一个新的QueryGenerator，以便级联调用
	 * */
	public QueryGenerator setOrderBy(String orderByName,String OrderBy) {
		this.OrderBy = orderByName + " " + OrderBy;
		return new QueryGenerator(this.query, this.displayColumn, this.TableName, this.Condition,
				this.GroupByColumnName,this.ConnectionCondition,this.OrderBy,this.Limit);
	}
	
	/**
	 * 设置最近多少条记录
	 * @param int limit 条数(初始为-1,即不限制条数)
	 * @return QueryGenerator 返回一个新的QueryGenerator，以便级联调用
	 * */
	public QueryGenerator setLimit(int limit) {
		this.Limit = limit;
		return new QueryGenerator(this.query, this.displayColumn, this.TableName, this.Condition,
				this.GroupByColumnName,this.ConnectionCondition,this.OrderBy,this.Limit);
	}
	
	public QueryGenerator Or() {
		this.ConnectionCondition.add("or");
		return new QueryGenerator(this.query, this.displayColumn, this.TableName, this.Condition,
				this.GroupByColumnName,this.ConnectionCondition,this.OrderBy,this.Limit);
	}
	
	public QueryGenerator And() {
		this.ConnectionCondition.add("and");
		return new QueryGenerator(this.query, this.displayColumn, this.TableName, this.Condition,
				this.GroupByColumnName,this.ConnectionCondition,this.OrderBy,this.Limit);
	}
	
	public String SelectQuery() {
		query += "Select ";
		
		for(int i = 0 ; i < displayColumn.size();i++) {
			if(i!=displayColumn.size()-1) {
				query += displayColumn.get(i) + " , ";
			}else {
				query += displayColumn.get(i) + "  ";
			}
		}
		
		query += " from ";
		
		for(int i = 0 ; i< TableName.size();i++) {
			if(i!=TableName.size()-1) {
				query += TableName.get(i) + " , ";
			}else {
				query += TableName.get(i) + "  ";
			}
		}
		
		if(Condition.size()!=0) {
			query += " where ";
			
			for(int i = 0 ; i < Condition.size();i++) {
				if(i!=Condition.size()-1) {
					query += Condition.get(i).getCondition() + "  " + ConnectionCondition.get(i) + "  ";
				}else {
					query += Condition.get(i).getCondition() + "  ";
				}
			}
		}
		
		if(GroupByColumnName.size()!=0) {
			query += " GroupBy ";
			
			for(int i = 0 ; i < GroupByColumnName.size();i++) {
				if(i!=GroupByColumnName.size()-1) {
					query += GroupByColumnName.get(i) + " , ";
				}else {
					query += GroupByColumnName.get(i) + "  ";
				}
			}
		}
		
		if(!OrderBy.equals("")) {
			query += " OrderBy " + OrderBy;
		}
		
		if(Limit!=-1) {
			query += " LIMIT " + String.valueOf(Limit);
		}

		
		return query;
	}
	
}
