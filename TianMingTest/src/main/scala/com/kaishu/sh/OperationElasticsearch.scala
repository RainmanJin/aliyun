package com.kaishu.sh

import java.net.{InetAddress, UnknownHostException}

import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest
import org.elasticsearch.common.settings.Settings
import org.elasticsearch.common.transport.{InetSocketTransportAddress, TransportAddress}
import org.elasticsearch.common.xcontent.{XContentFactory, XContentType}
import org.elasticsearch.index.query._
import org.elasticsearch.index.reindex.DeleteByQueryAction
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval
import org.elasticsearch.search.aggregations.bucket.terms.Terms
import org.elasticsearch.search.sort.SortOrder
import org.elasticsearch.transport.client.PreBuiltTransportClient

object OperationElasticsearch {
    def getTransportClient = {
        val settings = Settings.builder()
            .put("cluster.name", "prodcommon_es_cluster")
            .build()
        val client = new PreBuiltTransportClient(settings)
        try {
            val ips = List("172.16.12.221")
            for (ip <- ips) {
                client.addTransportAddresses(new InetSocketTransportAddress(InetAddress.getByName(ip), 9200))
            }
        } catch {
            case unknow: UnknownHostException => println(unknow.getMessage)
            case e: Exception => e.printStackTrace()
        }
        client
    }

    /**
      * 创建一个索引
      */
    def createIndex(index: String, ty: String): Unit = {
        val client = getTransportClient
        // 创建索引请求
        val createIndex = client.admin().indices().prepareCreate(index)
        // 创建mapping
        val mapping = XContentFactory.jsonBuilder()
            .startObject()
            // 设置定义字段
            .startObject("properties")
            // 设置分析器
            .startObject("name").field("type", "text").field("analyzed", "standard").endObject()
            .startObject("age").field("type", "keyword").endObject()
            .startObject("class_name").field("type", "keyword").endObject()
            // 设置日期格式
            .startObject("birth").field("type", "date").field("format", "yyyy-MM-dd").endObject()
            .endObject()
            .endObject()
        // 设置type和mapping
        createIndex.addMapping(ty, mapping)
        val res = createIndex.execute().actionGet()
    }

    /**
      * 插入一条记录
      *
      * @param jsonDocument json 文档
      * @param index        es 索引
      * @param ty           es type
      * @param id           es document id
      */
    def insertDocument(jsonDocument: String, index: String, ty: String, id: String = null): Unit = {
        val client = getTransportClient
        if (id != null) {
            client.prepareIndex(index, ty, id).setSource(jsonDocument, XContentType.JSON).execute().actionGet()
        } else {
            client.prepareIndex(index, ty).setSource(jsonDocument, XContentType.JSON).execute().actionGet()
        }
    }

    /**
      * 批量插入文档记录   注: 虽然能完成批量操作, 但是一次不要添加太多. 因为request的大小有限制.
      *
      * @param jsonDocuments json document
      * @param index         es 索引
      * @param ty            es type
      */
    def batchInsertDocument(jsonDocuments: List[String], index: String, ty: String): Unit = {
        val client = getTransportClient
        val bulkRequest = client.prepareBulk()
        for (jd <- jsonDocuments) {
            // 批量加载
            bulkRequest.add(client.prepareIndex(index, ty).setSource(jd, XContentType.JSON))
        }
        bulkRequest.execute().actionGet()
    }

    /**
      * 删除一个索引
      *
      * @param index es索引
      */
    def deleteIndex(index: String): Unit = {
        val client = getTransportClient
        val inExistsRequest = new IndicesExistsRequest(index)
        val existsResponse = client.admin().indices().exists(inExistsRequest).actionGet()

        // 存在则删除
        if (existsResponse.isExists) {
            client.admin().indices().prepareCreate(index).execute().actionGet()
        }
    }

    /**
      * 删除一条记录
      *
      * @param index 索引
      * @param ty    type
      * @param id    id
      */
    def deleteDocument(index: String, ty: String, id: String): Unit = {
        getTransportClient.prepareDelete(index, ty, id).execute().actionGet()
    }

    /**
      * 根据搜索返回值删除数据
      *
      * @param query 一种 QueryBuilder
      */
    def deleteDocument(query: QueryBuilder): Unit = {
        DeleteByQueryAction.INSTANCE.newRequestBuilder(getTransportClient).filter(query).execute().actionGet()
    }

    def updateDocument(index: String, ty: String, id: String): Unit ={
        val client = getTransportClient
        client.prepareUpdate("my","test","1")
            .setDoc("json", XContentType.JSON).get()
    }

    /**
      * 查询 数据
      *
      * @param index es索引
      */
    def queryBuilder(index: String): Unit = {
        val client = getTransportClient
        val matches = QueryBuilders.matchQuery("field", "text")
        val search = client.prepareSearch(index)
            .setQuery(matches)
            //            .setTypes("test") // es type 可选
            .setFrom(0).setSize(5) // 分页 可选
            .addSort("title", SortOrder.DESC) // 排序 可选

        // 返回搜索结果
        val response = search.get()
        // 命中的文档
        val hits = response.getHits
        // 命中总数
        val num = hits.getTotalHits
        // 循环查看命中值
        for (hit <- hits.getHits) {
            // 文档元数据
            val index = hit.getIndex
            // 文档的_source 的值:
            val sourceMap = hit.getSourceAsMap
        }
    }
}



class OperationElasticsearch{
    def getTransportClient = {
        val settings = Settings.builder()
            .put("cluster.name", "prodcommon_es_cluster")
            .build()
        val client = new PreBuiltTransportClient(settings)
        try {
            val ips = List("172.16.12.221")
            for (ip <- ips) {
                client.addTransportAddresses(new InetSocketTransportAddress(InetAddress.getByName(ip), 9200))
            }
        } catch {
            case unknow: UnknownHostException => println(unknow.getMessage)
            case e: Exception => e.printStackTrace()
        }
        client
    }


    def someQueryBuilder(): Unit ={
        val matchAll: QueryBuilder = QueryBuilders.matchAllQuery()

        val matchField: QueryBuilder = QueryBuilders.matchQuery("field", "text")

        val term: QueryBuilder = QueryBuilders.termQuery("field","text")
        val terms: QueryBuilder = QueryBuilders.termsQuery("field","text","text2","text3")

        val prefix: QueryBuilder = QueryBuilders.prefixQuery("field", "text")

        val fuzzy: FuzzyQueryBuilder = QueryBuilders.fuzzyQuery("field", "text")

        val wildcard: QueryBuilder = QueryBuilders.wildcardQuery("field", "patten")

        val queryString = QueryBuilders.queryStringQuery("queryString")


        /*
            多条件搜索需求：
            重要性比较高---> 标题包含Elasticsearch或JAVA
            内容同时包含Elasticsearch和JAVA
         */
        val boolQuery = QueryBuilders.boolQuery()
        val builder: BoolQueryBuilder = boolQuery.should(QueryBuilders.matchQuery("main_body", "Elasticsearch JAVA")
            .operator(Operator.AND).boost(1))
            .should(QueryBuilders.matchQuery("title", "Elasticsearch JAVA")
                .operator(Operator.OR).boost(10))

        /*
            聚合搜索Aggregation:
            聚合相当于SQL中的group，使用不同的AggregationBuilder能完成不同的聚合操作，
            这里列举几种聚合。 语法如下：
         */
        import org.elasticsearch.index.query.QueryBuilders
        import org.elasticsearch.search.aggregations.AggregationBuilders
        val client = getTransportClient
        //需要给聚合内容一个别名
        val aggregation = AggregationBuilders.terms("alias").field("field")
        val allQuery = QueryBuilders.matchAllQuery
        val response = client.prepareSearch("index").setQuery(allQuery).addAggregation(aggregation).get
        //根据别名获取聚合对象，不同聚合会返回不同的聚合对象
        val alias = response.getAggregations.get[Terms]("alias")
        for (entry: Terms.Bucket <- alias.getBuckets) {
            //聚合的属性值
            val value = entry.getKey.toString
            //聚合后的数量
            val count = entry.getDocCount
        }


        /*
            聚合某字段
         */
        val aggregation1 = AggregationBuilders
            .terms("alias").field("field")

        /*
            统计某字段的总数
         */
        val vcb = AggregationBuilders.count("alias").field("field")

        /*
            去重统计某字段的总数
         */
        val aggregation2 = AggregationBuilders.cardinality("alias").field("field")

        /*
            聚合时间
         */
        val aggregation3 = AggregationBuilders
            .dateHistogram("alias").field("field")
            .format("yyyy-MM-dd")
            .dateHistogramInterval(DateHistogramInterval.DAY)



    }
}
