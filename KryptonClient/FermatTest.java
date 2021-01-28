
import java.math.BigInteger;
import java.util.Random;

public class FermatTest
{

    private final static Random rand = new Random();
    BigInteger p;
    BigInteger q;
    private static BigInteger getRandomFermatBase(BigInteger n)
    {

        while (true)
        {
            final BigInteger a = new BigInteger (n.bitLength(), rand);
            // must have 1 <= a < n
            if (BigInteger.ONE.compareTo(a) <= 0 && a.compareTo(n) < 0)
            {
                return a;
            }
        }
    }

    public static boolean checkPrime(BigInteger n, int maxIterations)
    {
        if (n.equals(BigInteger.ONE))
            return false;

        for (int i = 0; i < maxIterations; i++)
        {
            BigInteger a = getRandomFermatBase(n);
            a = a.modPow(n.subtract(BigInteger.ONE), n);

            if (!a.equals(BigInteger.ONE))
                return false;
        }

        return true;
    }
    void returnIt()
    {
        FermatTest ft = new FermatTest();  
        String tp="";
        for(int i=0;i<15;i++)
        {
           double tpp;
          tpp=Math.random()*10000000;
          tp+=Integer.toString((int)tpp);
        }
        
         p = new BigInteger(tp);
        while(ft.checkPrime(p,20)==false)
        {
          
         
           p=p.add(BigInteger.ONE);
         
        }
        tp="";
        for(int i=0;i<15;i++)
        {
           double tpp;
          tpp=Math.random()*10000000;
          tp+=Integer.toString((int)tpp);
        }
         q = new BigInteger(tp);
        while(ft.checkPrime(q,20)==false)
        {
          
         
           q=q.add(BigInteger.ONE);
        }
       
    }
   BigInteger returnP()
   {
      return p;
    }
    BigInteger returnQ()
    {
      return q;
    }
}
