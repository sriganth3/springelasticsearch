package com.springelasticsearch.elk.data;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.SSLContext;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder.HttpClientConfigCallback;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;

@Configuration
public class ElasticConfiguration {

	@Value("${elastic.search.url}")
	String elkUrl;

	@Value("${elastic.search.api.key}")
	String apiKey;

	RestClient restClient = null;

	ElasticsearchClient elasticSearchClient = null;

	@Bean(name = "elasticClient")
	ElasticsearchClient elasticClient() {		

		if (restClient == null) {
			restClient = RestClient
					.builder(HttpHost.create(elkUrl))
					.setDefaultHeaders(new Header[]{
							new BasicHeader("Authorization", "ApiKey " + apiKey)
					})
					.build();
		}

		ElasticsearchTransport transport = new RestClientTransport(
				restClient, new JacksonJsonpMapper());

		elasticSearchClient = new ElasticsearchClient(transport);

		return elasticSearchClient;
	}

	public void closeElasticClient() {

		elasticSearchClient = null;
		if(restClient != null) {
			try {
				restClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			restClient = null;
		}
	}
	
	
	public RestClient configureSSL() throws KeyStoreException, CertificateException, IOException, NoSuchAlgorithmException, KeyManagementException{
		// Yet to be configured
		Path caCertificatePath = Paths.get("/path/to/ca.crt");
		CertificateFactory factory =
				CertificateFactory.getInstance("X.509");
		Certificate trustedCa;
		try (InputStream is = Files.newInputStream(caCertificatePath)) {
			trustedCa = factory.generateCertificate(is);
		}
		KeyStore trustStore = KeyStore.getInstance("pkcs12");
		trustStore.load(null, null);
		trustStore.setCertificateEntry("ca", trustedCa);
		SSLContextBuilder sslContextBuilder = SSLContexts.custom()
				.loadTrustMaterial(trustStore, null);
		final SSLContext sslContext = sslContextBuilder.build();
		RestClient.builder(
				new HttpHost("localhost", 9200, "https"))
		.setHttpClientConfigCallback(new HttpClientConfigCallback() {
			@Override
			public HttpAsyncClientBuilder customizeHttpClient(
					HttpAsyncClientBuilder httpClientBuilder) {
				return httpClientBuilder.setSSLContext(sslContext);
			}
		}).build();
		
		return restClient;
		
	}

}
