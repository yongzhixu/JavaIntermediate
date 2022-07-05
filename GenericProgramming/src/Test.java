
public class Test {

	
	static int maxSubarrayXOR(int arr[], int n) 
    { 
        int ans = Integer.MIN_VALUE; // Initialize result 
       
        // Pick starting points of subarrays 
        for (int i=0; i<n; i++) 
        { 
                // to store xor of current subarray    
            int curr_xor = 0;  
       
            // Pick ending points of subarrays starting with i 
            for (int j=i; j<n; j++) 
            { 
                curr_xor = curr_xor ^ arr[j]; 
                ans = Math.max(ans, curr_xor); 
            } 
        } 
        return ans; 
    } 
       
    // Driver program to test above functions 
    public static void main(String args[]) 
    { 
              
        String str1 = "String method tutorial";
        String str2 = "compareTo method example";
        String str3 = "tSring method tutorial";

        int var1 = str1.compareTo( str2 );
        System.out.println("str1 & str2 comparison: "+var1);

        int var2 = str1.compareTo( str3 );
        System.out.println("str1 & str3 comparison: "+var2);

        int var3 = str2.compareTo("compareTo method example");
        System.out.println("str2 & string argument comparison: "+var3);
        
        
        String s1 = new String("HELLO"); 
        String s2 = new String("HELLO"); 
        System.out.println(s1 == s2); 
        System.out.println(s1.equals(s2)); 
        System.out.println(s1.compareTo(s2)); 
    } 
}
