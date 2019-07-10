package fr.paris.lutece.plugins.librarymdph.utils;

public enum MdphConstant {

	// REQUEST HEADERS
	HEADER_KEY_CONTENT( "Content-Type" ),
	HEADER_KEY_AUTH( "Authorization" ),
	HEADER_VALUE_AUTH_PREFIX( "Bearer " ),
	HEADER_VALUE_CONTENT( "application/json" ),
	
	// JSON KEYS
	JSON_KEY_USER( "username" ),
	JSON_KEY_PWD( "password" ),
	JSON_KEY_ID_MDPH( "id_mdph" ),
	JSON_KEY_TYPE( "type_droit" ),
	JSON_KEY_STATUS( "statut_dossier" ),
	JSON_KEY_BIRTH_DATE( "date_naissance" ),
	JSON_KEY_NAME( "nom" ),
	JSON_KEY_DATE( "date_comparative" ),
	
	// JSON VALUES
	JSON_VALUE_TYPE( "AEEH" ),
	JSON_VALUE_STATUS( "Actif" ),
	
	// JSON RESPONSES,
	JSON_KEY_TOKEN( "token" ),
	JSON_KEY_CODE( "code" ),
	JSON_KEY_LABEL( "libelle" ),
	;
	
	private final String _value;
	
	private MdphConstant( String value )
	{
		_value = value;
	}
	
	public String getValue( )
	{
		return _value;
	}
}
