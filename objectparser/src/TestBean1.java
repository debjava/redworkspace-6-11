import java.lang.reflect.Field;

public class TestBean1 
{
	public static void main(String[] args) 
	{
		try 
		{
			Class cls = Class.forName("java.lang.String");//Bean
			Field[] fields = cls.getDeclaredFields();
			
			for( int i = 0 ; i < fields.length; i++ )
			{
				System.out.println(fields[i].getName()+"---"+fields[i].getType().toString());
			}
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}

}
