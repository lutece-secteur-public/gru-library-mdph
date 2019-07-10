package fr.paris.lutece.plugins.librarymdph.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class MdphRequest {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern( "dd/MM/yyyy" );
	
	private String _idMdph;
	
	private String _name;
	
	private LocalDate _birthDate;

	/**
	 * @param idMdph the idMdph to set
	 */
	public void setIdMdph( String idMdph )
	{
		_idMdph = idMdph;
	}

	/**
	 * @param name the name to set
	 */
	public void setNom( String name )
	{
		this._name = name;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate( LocalDate birthDate )
	{
		this._birthDate = birthDate;
	}
	
	public Map<String, String> createJsonMap( )
	{
		Map<String, String> map = new HashMap<>( );
		map.put( MdphConstant.JSON_KEY_ID_MDPH.getValue( ), _idMdph );
		map.put( MdphConstant.JSON_KEY_TYPE.getValue( ), MdphConstant.JSON_VALUE_TYPE.getValue( ) );
		map.put( MdphConstant.JSON_KEY_STATUS.getValue( ),MdphConstant.JSON_VALUE_STATUS.getValue( ) );
		map.put( MdphConstant.JSON_KEY_DATE.getValue( ), LocalDate.now( ).format( FORMATTER ) );
		map.put( MdphConstant.JSON_KEY_NAME.getValue( ), _name );
		map.put( MdphConstant.JSON_KEY_BIRTH_DATE.getValue( ), _birthDate.format( FORMATTER ) );
		return map;
	}
}
