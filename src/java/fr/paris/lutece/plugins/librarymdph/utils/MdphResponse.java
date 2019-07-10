package fr.paris.lutece.plugins.librarymdph.utils;

import fr.paris.lutece.portal.service.util.AppPropertiesService;

public class MdphResponse {

	private static final String DEFAULT_CODE = AppPropertiesService.getProperty( "mdph.default.mdph.code" );
	private static final String DEFAULT_LABEL = AppPropertiesService.getProperty( "mdph.default.mdph.label" );
	public static final MdphResponse DEFAULT_RESPONSE = new MdphResponse( DEFAULT_CODE, DEFAULT_LABEL );
	
	private final String _code;
	private final String _libelle;
	
	public MdphResponse( String code, String libelle )
	{
		this._code = code;
		this._libelle = libelle;
	}
	
	/**
	 * @return the code
	 */
	public String getCode( )
	{
		return _code;
	}
	
	/**
	 * @return the libelle
	 */
	public String getLibelle( )
	{
		return _libelle;
	}
}
