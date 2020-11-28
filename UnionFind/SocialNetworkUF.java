package UnionFind;
import java.io.*; 
import java.util.*;

public class SocialNetworkUF
{
    static Scanner sc;
    private int[] id;
    private int[] sz;
    
    public SocialNetworkUF(int N)
    {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++)
        {
            id[i] = i;
            sz[i] = 1;
        }
    }

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

    public void union(int p, int q, int N)
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
        int N = sc.nextInt();
        SocialNetworkUF uf = new SocialNetworkUF(N);
        int M = sc.nextInt();
        int m = 0;
        while(m < M)
        {
            int p = sc.nextInt();
            int q = sc.nextInt();
            String timeStamp = sc.next();
            if (!uf.connected(p, q) && uf.sz[p] != N)
            {
                uf.union(p, q, N);
            }
            else
            {
                System.out.println("All members are connected at: " + timeStamp);
                break;
            }
            m++;
        }
    }
}

/*
Input sets:

10
17
0 1 18:00:00
1 9 18:01:00
0 2 18:02:00
0 3 18:04:00
0 4 18:06:00
0 5 18:08:00
0 6 18:10:00
0 7 18:12:00
0 8 18:14:00
1 2 18:16:00
1 3 18:18:00
1 4 18:20:00
1 5 18:22:00
2 1 18:24:00
2 3 18:26:00
2 4 18:28:00
5 5 18:30:00
*/