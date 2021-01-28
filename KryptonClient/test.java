

public class test
{
    public static void main()
    {
     Blowfish b = new Blowfish();
     try
     {
     String s8_encrypt=b.decrypt("slhhl", "sahil");
               String s1 ="sahil";
              
            
            System.out.println(s8_encrypt);
        }
        catch(Exception ex){}
    }
}
