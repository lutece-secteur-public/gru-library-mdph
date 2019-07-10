package fr.paris.lutece.plugins.librarymdph.utils;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.util.httpaccess.HttpAccess;
import fr.paris.lutece.util.httpaccess.HttpAccessException;

public final class MdphConnection {
	/** The _client. */
    private final HttpAccess _clientHttp = new HttpAccess( );
    private final ObjectMapper _mapper = new ObjectMapper();
    
    private static final String URL = AppPropertiesService.getProperty( "mdph.ws.url" );
    private static final String ENDPOINT_LOGIN = AppPropertiesService.getProperty( "mdph.ws.login.endpoint" );
    private static final String ENDPOINT_MDPH = AppPropertiesService.getProperty( "mdph.ws.mdph.endpoint" );
    private static final String LOGIN = AppPropertiesService.getProperty( "mdph.ws.login.user" );
    private static final String PWD = AppPropertiesService.getProperty( "mdph.ws.login.pwd" );
    
    public String callLogin( )
    {
    	Map<String, String> headerMap = initHeaderMap();
    	
    	Map<String, String> jsonMap = new HashMap<>( );
		jsonMap.put( MdphConstant.JSON_KEY_USER.getValue(), LOGIN );
		jsonMap.put( MdphConstant.JSON_KEY_PWD.getValue(), PWD );
		
		try {
			String jsonReponse = _clientHttp.doPostJSON( URL + ENDPOINT_LOGIN , _mapper.writeValueAsString( jsonMap ), headerMap, null );
			JsonElement jelementLogin = new JsonParser( ).parse( jsonReponse );
			JsonObject jobjectLogin = jelementLogin.getAsJsonObject( );
			return jobjectLogin.get( MdphConstant.JSON_KEY_TOKEN.getValue( ) ).getAsString( );
		} catch (JsonProcessingException | HttpAccessException e) {
			AppLogService.error( "Mdph Login error", e );
			return null;
		}
    }
    
    public String callMdph( Map<String, String> jsonMap, String token )
    {
    	Map<String, String> headerMap = initHeaderMap();
    	headerMap.put( MdphConstant.HEADER_KEY_AUTH.getValue( ), MdphConstant.HEADER_VALUE_AUTH_PREFIX.getValue( )  + token );
    	
    	String jsonReponse = null;
    	try {
			jsonReponse = _clientHttp.doPostJSON( URL + ENDPOINT_MDPH, _mapper.writeValueAsString( jsonMap ), headerMap, null );
		} catch (JsonProcessingException | HttpAccessException e) {
			AppLogService.error( "Mdph Call error", e );
			return null;
		}
    	return jsonReponse;
    }
    
    private Map<String, String> initHeaderMap( )
    {
    	Map<String, String> headerMap = new HashMap<>( );
		headerMap.put( MdphConstant.HEADER_KEY_CONTENT.getValue( ), MdphConstant.HEADER_VALUE_CONTENT.getValue( ) );
		return headerMap;
    }
}
