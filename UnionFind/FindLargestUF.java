package UnionFind;
import java.io.*; 
import java.util.*;

public class FindLargestUF
{
    static Scanner sc;
    private int[] id;
    private int[] sz;
    private int[] l;
    
    public FindLargestUF(int N)
    {
        id = new int[N];
        sz = new int[N];
        l = new int[N];
        for (int i = 0; i < N; i++)
        {
            id[i] = i;
            sz[i] = 1;
            l[i] = i;
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
            l[i] = l[j] = l[i] > l[j] ? l[i] : l[j];
        }
        else
        {
            id[j] = i;
            sz[i] += sz[j];
            l[i] = l[j] = l[i] > l[j] ? l[i] : l[j];
        }
    }

    public int find(int i)
    {
        return l[root(i)];
    }

    public static void main(String[] args)
    {
        try {  
            sc = new Scanner(new FileReader("input.txt"));
        } catch(Exception e) {
            System.out.println(e);
        }
        int N = sc.nextInt();
        FindLargestUF uf = new FindLargestUF(N);
        int M = sc.nextInt();
        int m = 0;
        while(m < M)
        {
            int p = sc.nextInt();
            int q = sc.nextInt();
            if (!uf.connected(p, q))
            {
                uf.union(p, q);
                System.out.println(p + " " + q);
            }
            m++;
        }
        System.out.println("Largest element in the connected component containing 0 is " + uf.find(0));
    }
}

/*
Input set:

10
11
4 3
3 8
6 5
9 4
2 1
8 9
5 0
7 2
6 1
1 0
6 7
*/