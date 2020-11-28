import java.io.*; 
import java.util.*;

public class QuickFindUF
{
    static Scanner sc;
    private int[] id;
    private int[] sz;
    
    public QuickFindUF(int N)
    {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++)
        {
            id[i] = i;
            sz[i] = 1;
        }
    }

    // It takes N 2 array accesses to process a sequence of N union commands on N objects.
    /*
    public boolean connected(int p, int q)
    { 
        return id[p] == id[q];
    }

    public void union(int p, int q)
    {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++)
            if (id[i] == pid)
                id[i] = qid;
    }
    */

    // Find too expensive (could be N array accesses)

    private int root(int i)
    {
        while (i != id[i])
        {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q)
    {
        return root(p) == root(q);
    }

    public void union(int p, int q)
    {
        int i = root(p);
        int j = root(q);
        if (i == j)
            return;
        if (sz[i] < sz[j])
        {
            id[i] = j;
            sz[j] += sz[i];
        }
        else
        {
            id[j] = i;
            sz[i] += sz[j];
        }
    }

    public static void main(String[] args)
    {
        try {  
            sc = new Scanner(new FileReader("input.txt"));
        } catch(Exception e) {
            System.out.println(e);
        }

        int T = sc.nextInt();
        int t = 0;
        
        while(t < T)
        {
            int N = sc.nextInt();
            QuickFindUF uf = new QuickFindUF(N);
            int Q = sc.nextInt();
            int qs = 0;
            while(qs < Q)
            {
                int p = sc.nextInt();
                int q = sc.nextInt();
                if (!uf.connected(p, q))
                {
                    uf.union(p, q);
                    System.out.println(p + " " + q);
                }
                qs++;
            }
            t++;
        }
    }
}