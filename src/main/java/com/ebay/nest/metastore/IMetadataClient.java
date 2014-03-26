package com.ebay.nest.metastore;

public abstract interface IMetadataClient {
	public abstract void connect() throws MetaException;

	public abstract void reconnect() throws MetaException;

	public abstract void close();

	public abstract TableMetadata getMetadata(String paramString1, String paramString2) throws MetaException;
}