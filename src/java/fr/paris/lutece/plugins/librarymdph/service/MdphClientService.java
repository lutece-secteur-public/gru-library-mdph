package fr.paris.lutece.plugins.librarymdph.service;

import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import fr.paris.lutece.plugins.librarymdph.utils.MdphConnection;
import fr.paris.lutece.plugins.librarymdph.utils.MdphConstant;
import fr.paris.lutece.plugins.librarymdph.utils.MdphRequest;
import fr.paris.lutece.plugins.librarymdph.utils.MdphResponse;
import fr.paris.lutece.portal.service.util.AppLogService;

/**
 * Service calling MDPH API. 
 */
public class MdphClientService {

	public static final MdphClientService INSTANCE = new MdphClientService( );
	
	private final MdphConnection _connection = new MdphConnection();
	
	private MdphClientService( )
	{
		
	}
	
	public static MdphClientService getInstance( )
	{
		return INSTANCE;
	}
	
	public MdphResponse callMdph( MdphRequest mdphRequest )
	{
		AppLogService.debug("Calling login API");
		String token = _connection.callLogin( );
		if ( token == null )
		{
			AppLogService.debug( "Login Error" );
			return MdphResponse.DEFAULT_RESPONSE;
		}
		
		Map<String, String> jsonMap = mdphRequest.createJsonMap( );
		
		AppLogService.debug( "Calling mdph API" );
		String jsonReponse = _connection.callMdph( jsonMap, token );
		if ( jsonReponse == null )
		{
			AppLogService.debug( "Error mdph" );
			return MdphResponse.DEFAULT_RESPONSE;
		}
		
		JsonElement jelement = new JsonParser( ).parse( jsonReponse );
		JsonObject jobject = jelement.getAsJsonObject( );
		if ( !jobject.has( MdphConstant.JSON_KEY_CODE.getValue( ) ) || !jobject.has( MdphConstant.JSON_KEY_LABEL.getValue( ) ) )
		{
			AppLogService.debug( "Error parsing response" );
			return MdphResponse.DEFAULT_RESPONSE;
		}
		MdphResponse response = new MdphResponse( jobject.get( MdphConstant.JSON_KEY_CODE.getValue( ) ).getAsString( ), jobject.get( MdphConstant.JSON_KEY_LABEL.getValue( ) ).getAsString( ) );
		AppLogService.debug("MDPH response: " + response.getCode( ) + " " + response.getLibelle( ) );
		return response;
	}
}
