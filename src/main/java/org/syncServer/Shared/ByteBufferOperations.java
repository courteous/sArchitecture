package org.syncServer.Shared;

import java.nio.ByteBuffer;
import java.util.UUID;

public class ByteBufferOperations {
	
	
	public UUID ByteBufferToUUID (ByteBuffer uuid)
	{
		UUID id = null;
		try {
			uuid.flip();
			id = new UUID(uuid.getLong(),uuid.getLong());
			
		} catch (Exception e) {
			// TODO: logger here
		}
		return id;
	}
	
	public String ByteBufferToString (ByteBuffer uuid)
	{
		String string_id = null;
		try {
			uuid.flip();
			UUID id = new UUID(uuid.getLong(),uuid.getLong());
			string_id = id.toString().trim();
			
		} catch (Exception e) {
			// TODO: logger here
		}
		return string_id;
	}
	
	public ByteBuffer UUIDToByteBuffer (UUID uuid)
	{
		ByteBuffer id = null;
		try {
			id = ByteBuffer.wrap(new byte[16]);
			id.putLong(uuid.getMostSignificantBits() );
			id.putLong(uuid.getLeastSignificantBits());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return id;
	
	}
	
	
	

}
