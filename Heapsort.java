package Heapsort1;

//Heapsort1 is modified from Heap from https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/Heap.java.html
//JavaDoc https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/Heap.html
public class Heapsort1 {

	public class Heap {
		private static int numCompares = 0;

	    // This class should not be instantiated.
	    private Heap() { }

	    /**
	     * Rearranges the array in ascending order, using the natural order.
	     * @param pq the array to be sorted
	     */
	    public static void sort(Comparable[] pq) {
	        int n = pq.length;

	        // heapify phase
	        for (int k = n/2; k >= 1; k--)
	            sink(pq, k, n);
	        
	        // sortdown phase
	        int k = n;
	        while (k > 1) {
	            exch(pq, 1, k--);
	            sink(pq, 1, k);
	        }
	    }
	    
	    public static void sort2(Comparable[] pq) {
	        int n = pq.length;

	        // heapify phase
	        for (int k = n/2; k >= 1; k--)
	            sink(pq, k, n);
	        
	        // sortdown phase	        
        	int k = n;	        
	        while(k>2) {	        	      //調整到node2停止
		        exch(pq,1,k--);               //exchange root and last node
		        
	        	if(!less(pq, 2, 3)) {         //if node2 large then node3 
	        	   exch(pq, 2, k--);          //exchange node2 and k
		           sink(pq, 2, k);            //modify subtree
	        	}
	        	else {                        //if node3 large then node2
	        		exch(pq, 3, k--);         //exchange node3 and k
			        sink(pq, 3, k);           //modify subtree
	        	}
	        	sink(pq, 1, k); 	          ////modify tree
	        }
		
	    }
	    
	   /***************************************************************************
	    * Helper functions to restore the heap invariant.
	    ***************************************************************************/

	    private static void sink(Comparable[] pq, int k, int n) {
	        while (2*k <= n) {
	            int j = 2*k;
	            if (j < n && less(pq, j, j+1)) j++;
	            if (!less(pq, k, j)) break;
	            exch(pq, k, j);
	            k = j;
	        }
	    }

	   /***************************************************************************
	    * Helper functions for comparisons and swaps.
	    * Indices are "off-by-one" to support 1-based indexing.
	    ***************************************************************************/
	    private static boolean less(Comparable[] pq, int i, int j) {
	    	numCompares++;
	        return pq[i-1].compareTo(pq[j-1]) < 0;
	    }

	    private static void exch(Object[] pq, int i, int j) {
	        Object swap = pq[i-1];
	        pq[i-1] = pq[j-1];
	        pq[j-1] = swap;
	    }
	    
	    // print array to standard output
	    private static void show(Comparable[] a) {
	        for (int i = 0; i < a.length; i++) {
	            System.out.print(a[i] + " ");
	        }
            System.out.println();
            System.out.print("Number of compares: " + numCompares);
            System.out.println();
            numCompares = 0;
	    }
	    
	    private static void showImprovement(int compares1, int compares2) {
	    	System.out.println("Improvement: " + String.format("%.2f", 100*(compares1-compares2)/(double)compares1) + "%");
	    	System.out.println();
	    }
	    
	    private static int getCompares() {
	    	return numCompares;
	    }

	    /**
	     * Reads in a sequence of strings from standard input; heapsorts them; 
	     * and prints them to standard output in ascending order. 
	     *
	     * @param args the command-line arguments
	     */
	}
	
    public static void main(String[] args) {
    	Integer[] a = {4,1,5,3,2,8,9,2,1,6,1,1,6,2,5,15,20,11};
    	Heap.sort(a);
        int compares1 = Heap.getCompares();
        Heap.show(a);        
        a = new Integer[]{4,1,5,3,2,8,9,2,1,6,1,1,6,2,5,15,20,11};
        Heap.sort2(a);
        int compares2 = Heap.getCompares();
        Heap.show(a);
        Heap.showImprovement(compares1, compares2);
        
        Integer[] b = {1,5,3,2,8,10,2,1,6,7,22,1,10,15,11,19,1,5,5,27,5,16};
    	Heap.sort(b);
        compares1 = Heap.getCompares();
        Heap.show(b);
        b = new Integer[]{1,5,3,2,8,10,2,1,6,7,22,1,10,15,11,19,1,5,5,27,5,16};
        Heap.sort2(b);
        compares2 = Heap.getCompares();
        Heap.show(b);
        Heap.showImprovement(compares1, compares2);
    }
}
