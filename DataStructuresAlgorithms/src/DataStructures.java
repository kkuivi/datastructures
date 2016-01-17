import java.util.ArrayList;

public class DataStructures {
	public static void main(String args[]) throws Exception{
		
	}
	
	/*Prints the elements in an array*/
	static void printarr(String method, int[] arr){
		System.out.println(method+ ":");
		for(int i= 0; i< arr.length; i++){
			System.out.print(arr[i] + ",");
		}
		System.out.println();
	}
	
	/*Returns an boolean array storing whether all odd numbers from 0 to max+1 are prime
	 * or not*/
	static boolean[] sieveofEratosthenes(int max){
		boolean[] 	flags = new boolean[(max + 1)/2];
		init(flags,  true); //initialize every index in flags to true
		int prime_index = 1; //start with the index 1 of flags[] which represents 2(1) + 1 = 3
		while(prime_index > 0 && prime_index < flags.length){
			cancelMultiples(prime_index, flags); //go through flags[] and set all multiples of 2(prime_index) + 1 to false
			prime_index= nextPrimeIndex(flags, prime_index); //find the next prime number after prime_index
		}
		return flags;
	}
	
	/*Initialize every index in the boolean array to the value of b*/
	static void init(boolean[] flags, boolean b){
		for(int i = 0; i < flags.length; i ++){
			flags[i] = b;
		}
	}
	
	/*go through boolean array and set the index of all multiples of 2(prime_index) + 1 to 
	 * false*/
	static void cancelMultiples(int prime_index, boolean[] flags){
		int prime = 2*prime_index + 1;
		int p_index = (prime*prime)/2; //stores index of prime*prime in the boolean array
		/*for loop starts from the index of prime*prime because if every multiple of k*prime
		 * where k < prime  has been canceled, then we just need to start from prime*prime and cancel
		 * its multiples*/
		for(int i = p_index; i < flags.length; i += prime){
			flags[i] = false;
		}
	}
	
	/*returns the index of the next prime number after prime_index*/
	static int nextPrimeIndex(boolean[] flags, int prime_index){
		int p_index = ++prime_index;
		while(p_index < flags.length){
			if(flags[p_index] == true){
				return p_index;
			}
			p_index ++;
		}
		return -1; //return -1 if there's no other prime number after prime_index
	}
	
	/*returns the largest prime number in the boolean array*/
	static int findLargestPrime(boolean[] prime_checker){
		for(int i = prime_checker.length - 1; i >= 0; i --){
			if(i == 0) //since each index in prime_checker represents 2*i + 1, 
					  //if i == 0, then the largest prime should be 2 not 1 
				return 2;
	
			else if(prime_checker[i] == true)
				return 2*i + 1;
		}
		return -1; //returns -1 if there's no prime number in the boolean array
	}
	
	/*HashTable class*/
	 static class HashTable{
		/*This implementation will have a LinkedList at each index of the Hash Table*/
		Node[] indices;
		
		HashTable(int size){
			boolean[] prime_checker = sieveofEratosthenes(size);//stores whether numbers from 0 to size +1 is prime
			int closest_prime = findLargestPrime(prime_checker); //returns the largest prime number from the prime_checker array
			this.indices = new Node[closest_prime]; //sets the length of the indices array to a prime number closest to the size variable
			/*Initialize the indices array with an empty LinkedList at each index*/
			for(int i = 0; i < indices.length; i ++)
				indices[i] = new Node();
		}
		

		int hashcode(int x){
			int result = (int) Math.pow(x,x);
			return result;
		}
		
		void hash(int key, int value){
			int hc = hashcode(key); //store hashcode for x
			int index = hc % indices.length;
			this.indices[index].append(key, value);
		}
		
		int get(int key){
			int hc = hashcode(key);
			int index = hc % indices.length;
			return indices[index].get(key);
		}
	}
	
	 /*Node Class which implements the LinkedList data structure*/
	static class Node{
		/*Each Node of the LinkedList has a unique key and the data it holds
		 * This unique key is used because it will be used by the hash table class*/
		Node next = null;
		int data;
		int key;
		
		Node(int k, int d){
			this.key  = k;
			this.data = d;
		}
		
		//Empty constructor that just sets data and key to null
		Node(){}
		
		//Add a new Node to the end of the LinkedList
		void append(int k, int v){
			Node x = new Node(k, v);
			Node n = this;
			
			
			while(n.next != null){
				n = n.next;
			}
			n.next = x;
		}
		
		
		int get(int k){
			Node n = this.next;
	
			while(n != null){
				if(n.key == k)
					return n.data;
				else
					n = n.next;
			}
			return -1; // returns -1 if Node with key k cannot be found in the LinkedList
		}
	}
	
	/*Implementation of a StringBuilder*/
	static class StringBuilder{
		ArrayList<String> words;
		
		StringBuilder() {
			words = new ArrayList<String> ();
		}
		
		void append(String s){
			words.add(s);
		}
		
		
		String changeToString(){
			return words.toString().replaceAll("\\[|\\]|,", ""); //removes all "[", "]", "," from the String value of words returned
		}	
	}
	
	/*Implementation of an ArrayList. Size of Array grows by 2 every time it's full*/
	static class mArrayList{
		int [] arr;
		int empty_index; //keeps track of the empty index in arr
		
		mArrayList(){
			arr = new int[1];
			empty_index = 0;
		}
		
		void add(int x){
			arr[empty_index] = x;
			empty_index ++;
			if(full()){
				resize();
			}
		}
		
		boolean full(){
			if(empty_index == arr.length)
				return true;
			else
				return false;
		}
		
		//increase size of arr by 2
		void resize(){
			int[] n_arr = new int[2*arr.length];
			for(int i = 0; i < arr.length; i++)
				n_arr[i] = arr[i];
			arr = n_arr;
		}
		
		int find(int num){
			for(int i = 0; i < arr.length; i++){
				if(arr[i] == num)
					return i;
			}
			return -1;
		}
		
		int arr_size(){
			return arr.length;
		}
		
		int get(int index){
			return arr[index];
		}
	}
	
	/*Implementation of the Queue Data Structure*/
	static class MyQueue<T>{
		private static class QueueNode<T>{
			private T data;
			private QueueNode<T> next;
			
			private QueueNode(T d){
				data = d;
			}
		}
		
		private QueueNode<T> first; 
		private QueueNode<T> last;
		
		void add(T d){
			QueueNode<T> item = new QueueNode<T>(d);
			if (first == null){
				first = item;
				last = first;
			}
			else{
				last.next = item;
				last  = item;}
			}
		
		
		T remove() throws EmptyQueueException{
			T item;
			if(first == null)
				throw new EmptyQueueException();
			else if(first == last){
				item = first.data;
				first = null;
				last = first;
				return item;
			}
			item = first.data;
			first = first.next;
			return item;
		}
		
		boolean empty(){
			return first==null;
		}
	}
	
	/*Implementation of Queue that only stores integers*/
	static class MyIntQueue{
		private static class QueueNode{
			private int data;
			private QueueNode next;
			
			private QueueNode(int d){
				data = d;
			}
		}
		
		private QueueNode first;
		private QueueNode last;
		
		void add(int d){
			QueueNode item = new QueueNode(d);
			if (first == null){
				first = item;
				last = first;
			}
			else{
				last.next = item;
				last  = item;}
			}
		
		int remove() throws Exception{
			int item;
			if(first == null)
				throw new Exception();
			else if(first == last){
				item = first.data;
				first = null;
				last = first;
				return item;
			}
			item = first.data;
			first = first.next;
			return item;
		}
		
		boolean empty(){
			return first==null;
		}
	}
	
	
	/*Implementation of the Stack Data Structure*/
	static class MyStack<T>{
		private static class StackNode<T>{
			private T data;
			private StackNode<T> next;
			
			StackNode(T d){
				data = d;
			}
		}
		
		private StackNode<T> top;
		
		void push(T d){
			StackNode<T> item = new StackNode<T>(d);
			if(top == null)
				top = item;
			else{
				item.next = top;
				top = item;
			}
		}
		
		T pop() throws EmptyStackException{
			T item = null;
			if(top == null)
				throw new EmptyStackException();
			else{
				item = top.data;
				if(top.next == null)
					top = null;
				else
					top = top.next;
			}
			return item;
		}
		
		T peek() throws Exception{
			if(top  == null)
				throw new Exception();
			else
				return top.data;
		}
		
		boolean empty(){
			return top == null;
		}
	}
	
	/*Implementation of a binary tree data structure that builds a complete tree*/
	static class TreeNode<T>{
		T data;
		TreeNode<T> left;
		TreeNode<T> right;
		
		TreeNode (T d){
			data = d;
		}
		
		void add(T d) throws Exception{
			MyQueue<TreeNode<T>> next_nodes = new MyQueue<TreeNode<T>>(); //stores next nodes to view/touch
			TreeNode<T> node = new TreeNode<T>(d);
			boolean finished = false; //stores whether the new node has been added to the tree or not
			
			if(left == null)
				left = node;
			else if(right == null)
				right = node;
			else{
				TreeNode<T> node2 = left;
				next_nodes.add(right);
				while(finished == false){
					if(node2.left == null){
						node2.left = node;
						finished = true;
					}
					else if(node2.right == null){
						node2.right = node;
						finished =true;
					}
					else{
						next_nodes.add(node2.left);
						next_nodes.add(node2.right);
						node2 = next_nodes.remove(); ///store next node to explore
						}
					}
				}		
			}
		}
	
	/*Implementation of the Post Order Binary Tree Traversal*/
	static void postOrderTrav(TreeNode<?> root){
		if(root != null){
			postOrderTrav(root.left);
			postOrderTrav(root.right);
			System.out.println(root.data);
		}
	}
	
	/*Implementation of the Pre-Order Binary Tree Traversal*/
	static void preOrderTrav(TreeNode<?> root){
		if(root != null){
			System.out.println(root.data);
			preOrderTrav(root.left);
			preOrderTrav(root.right);
		}
	}
	
	/*Implementation of the In-Order Tree Traversal*/
	static void inOrderTrav(TreeNode<?> root){
		if(root != null){
			inOrderTrav(root.left);
			System.out.println(root.data);
			inOrderTrav(root.right);
		}
	}
	
	/*Implementation of a Binary Search Tree Node*/
	class BSNode {
		int data;
		BSNode left;
		BSNode right;
		
		BSNode(int d){
			data = d;
		}
		
		void add(int d){
			BSNode node = this;
			BSNode x = new BSNode(d);
			boolean added = false ; //checks if node added 
			
			while(added == false){
				if(d <= data){
					if(node.left == null){
						node.left = x;
						added = true;
					}
					else
						node = node.left;
				}
				else{
					if(node.right == null){
						node.right = x;
						added = true;
					}
					else
						node  = node.right;
				}
			}
		}
	}
	
	
	/* Implementation of the bubblesort Algorithm*/
	static void bubbleSort(int[] arr){
		int max_touches = arr.length;//will store the max number of nodes to be sorted 
									
		boolean sorted = false; //checks whether arr is already sorted
		
		/*max_touches has to be greater than 1 because once we have only one element to touch
		 * then it implies the array has been sorted because bubblesort sorts from the end of the array*/
		while(max_touches > 1){
			int count2 = 0;
			/*since bubblesort sorts from the end of the array, each iteration
			goes until the number of elements to be touched. The -1 is there because each iteration compares
			arr[count] and arr[count2 + 1]*/
			while(count2 < max_touches - 1){
				sorted = true; /*assume the array is already sorted; however, if we do a swap
				then it means arr is not already sorted*/
				if(arr[count2] > arr[count2+1]){
					sorted = false;
					swap(arr, count2, count2 + 1); //swap values at current index and the adjacent index
				}
				count2 ++;
			}
			if(sorted)
				break;
			
			max_touches --; //reduce the number of elements to be touched minus one
		}
	}
	
	//swap the elements of arr at index1 and index2
	static void swap(int[] arr, int index1, int index2){
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}
	
	/*Implementaion of the Selection Sort Algorithm*/
	static void selectionSort(int[] arr){
		int max_touches = arr.length; //will store maximum number of elements to be sorted
		boolean sorted = false; //stores whether the array is already sorted
		
		/*max_touches has to be greater than 1 because once we have only one element to touch
		 * then it implies the array has been sorted because selectionsort sorts from the end of the array*/
		while(max_touches > 1){
			int c2 = 0;
			int max_index = max_touches - 1; //we will assume the maximum element is at the last element to be touched
											// it is max_touches - 1 because max_touches initially equals arr.length
			sorted = true; //we will assume the array is sorted, and update if wrong
			
			/*since selectionsort sorts from the end of the array, each iteration
			goes until the number of elements to be touched. The -1 is there because the first iteration already
			checks if arr[c2] >= arr[max_index] before max_index gets updated in the following iterations*/
			while(c2 < max_touches - 1){
				if(arr[c2] >= arr[max_index]){
					sorted = false; //once we need to do a swap then it implies the array is not already sorted
					max_index = c2;
				}
				c2 ++;
			}
			if(sorted)
				break;
			
			swap(arr, max_index, max_touches -1); //swap the largest so far with the last element of the unsorted part of the array 
			max_touches --; 
		}
	}
	
	/*Implementation of the Merge Sort Algorithm*/
	static void mergeSort(int[] arr){
		int[] helper = new int[arr.length]; //temporarily stores the elements of arr
		int left = 0;
		int right = arr.length -1;
		mergeSort(arr, helper, left, right);
	}
	
	static void mergeSort(int[] arr, int[] helper, int left, int right){
		if(left < right){
			int middle = (left + right)/2;
			mergeSort(arr, helper, left, middle);
			mergeSort(arr, helper, middle + 1, right);
			merge(arr, helper, left, middle, right);
		}
	}
	
	static void merge(int[] arr, int[] helper, int low, int middle, int high){
		for(int i = 0; i <= high; i ++){
			//copy elements in the array into a helper array that will temporarily store the current elements in arr
			helper[i] = arr[i];
		}
		int curr = low;
		int helperLeft = low;
		int helperRight = middle + 1;
		while(helperLeft <= middle && helperRight <= high){
			if(helper[helperLeft] > helper[helperRight]){
				arr[curr] = helper[helperRight];
				helperRight ++;
			}
			else{
				arr[curr] = helper[helperLeft];
				helperLeft ++;
			}
			curr ++;
		}
		
		int remaining = middle - helperLeft; //store number of remaining elements in the left side of arr
		//copy remaining elements in the left side of helper array into the remaining places in arr
		for(int  i = 0; i <= remaining; i ++){
			arr[curr + i] = helper[helperLeft + i];
		}
	}
	
	/*Implementaion of the QuickSort Algorithm*/
	static void quicksort(int[] arr){
		int low = 0;
		int high = arr.length - 1;
		quicksort(arr, low, high);
	}
	
	static void quicksort(int[] arr, int low, int high){
		int index = partition(arr, low, high); //returns index of partition element
		if(low < index)
			quicksort(arr, low, index);
		if(index+1 < high)
			quicksort(arr, index, high);
	}
	
	static int partition(int[] arr, int low, int high){
		int mid = (low + high)/2;
		while(low <= high){
			while(arr[mid] > arr[low]) low++;
			while(arr[mid] < arr[high]) high --;
			if(low <= high){
				swap(arr, low, high);
				low ++;
				high --;
			}
		}
		return mid; //returns index of partition element
	}
	
	/*Implementation of the RadixSort Algorithm*/
	static void radixsort(int[] arr, int passes) throws Exception{
		int i = 0; //used to keep track of passes of bucketsort done
		while(i < passes){
			bucketsort(arr, i);
			i ++;
		}
	}
	
	static void bucketsort(int[] arr, int leastSigDig) throws Exception{
		MyIntQueue[] buckets = new MyIntQueue[10]; //each index corresponds to a digit 0-9
		
		
		//initialize buckets with Queues
		for(int i = 0; i < buckets.length; i ++){
			buckets[i] = new MyIntQueue();
		}
		
		for(int i = 0; i < arr.length; i ++){
			int num = arr[i];
			int sig_dig = digitAtIndex(num, leastSigDig); //returns the least significant digit of number
			buckets[sig_dig].add(num);
		}
			 
		int count2 = 0;
		for(int i = 0; i < arr.length; i ++){
			while(buckets[count2].empty() && count2 < buckets.length)count2++; //once a queue is empty, move to the next queue containing elements
			arr[i] = buckets[count2].remove();
		}
	}
	
	static int digitAtIndex(int num, int index){
		//assuming least significant digit starts at index 0
		int divider = (int) Math.pow(10,  index);
		int quotient = num / divider;
		return quotient%10;
	}
	
	/*Implementation of an iterative approach for the Binary Search Algorithm*/
	static int binarysearch(int[] arr, int n){
		int low = 0;
		int high = arr.length - 1;
		while(low <= high){
			int mid = (low + high)/2;
			if(n> arr[mid])
				low = mid + 1;
			else if(n < arr[mid])
				high = mid - 1;
			else
				return mid;
		}
		return -1; //returns -1 if item not found
	}
	
	/*Implementation of a recursive approach of the Binary Search Algorithm*/
	static int binarySearchRecursive(int[] arr, int n){
		int low = 0;
		int high = arr.length - 1;
		return binarySearchRecursive(arr, n, low, high);
	}
	
	static int binarySearchRecursive(int[] arr, int n, int low, int high){
		if(low <= high){
			int mid = (low + high)/2;
			if(n > arr[mid])
				return binarySearchRecursive(arr, n, mid + 1, high);
			else if(n < arr[mid])
				return binarySearchRecursive(arr, n, low, mid - 1);
			else
				return mid;
		}
			return -1; //error item not found
	}
}
