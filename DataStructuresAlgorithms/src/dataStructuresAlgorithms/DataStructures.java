package dataStructuresAlgorithms;
import java.util.ArrayList;
import java.util.Scanner;

public class DataStructures {
	public static void main(String args[]) throws Exception{
		/*HashTable h = new HashTable(100);
		h.hash(1, 1);
		h.hash(2, 4);
		h.hash(3, 9);
		System.out.println(h.get(1) + " " + h.get(2) + " " + h.get(3) + " " + h.get(5));
		
		String[] word_list = {"my", "name", "is", "elikem"};
		StringBuilder sentence = new StringBuilder();
		for(String w: word_list){
			sentence.append(w);
		}
		System.out.println(sentence.changeToString());
		
		mArrayList arr = new mArrayList();
		System.out.println(" size: " + arr.arr_size());
		arr.add(1);
		System.out.println(arr.find(1) + " size: " + arr.arr_size());
		arr.add(3);
		System.out.println(arr.find(3) + " size: " + arr.arr_size());
		arr.add(-1);
		System.out.println(arr.find(-1) + " size: " + arr.arr_size());
		System.out.println(arr.get(0) + " " + arr.get(1) + " " + arr.get(2)); */
		
		/*MyStack<Integer> stack = new MyStack<Integer>();
		stack.push(5);
		stack.push(4);
		stack.push(3);
		System.out.println(stack.pop() + " " + stack.pop() + " " + stack.pop());
		
		
		MyQueue<String> queue = new MyQueue<String>();
		queue.add("my");
		queue.add("name");
		queue.add("elikem");
		System.out.println(queue.remove() + " " + queue.remove() + " " + queue.remove());*/
		
		/*TreeNode<Integer> t = new TreeNode<Integer>(3);
		t.add(4);
		t.add(5);
		t.add(8);
		t.add(9);
		
		postOrderTrav(t); */
		
		/*int[] arr = {1,2,4,3,5,6,7};
		bubbleSort(arr);
		printarr("bubblesort",arr);
		int[] arr2 = {1,2,3,4,5,6,7};
		selectionSort(arr2);
		printarr("selectionsort", arr2);
		int[] arr3 = {1,2,3,4,5,6,7,8};
		mergeSort(arr3);
		printarr("mergesort", arr3);
		int[] arr4 = {7,3,5,8,5,1};
		quicksort(arr4);
		printarr("quicksort",arr4);
		int[] arr5 = {10, 11, 9, 8, 100, 250};
		radixsort(arr5, 3);
		printarr("radixsort", arr5);*/
		
		//int[] search_arr = {};
		//System.out.println(binarysearch(search_arr,1));
		
		//int[] search_arr = {-1,0,3,5,7,8};
		//System.out.println(binarySearchRecursive(search_arr, 2));
		
	}
	
	/*Prints the elements in an arr*/
	static void printarr(String method, int[] arr){
		System.out.println(method+ ":");
		for(int i= 0; i< arr.length; i++){
			System.out.print(arr[i] + ",");
		}
		System.out.println();
	}
	
	/*HashTable class*/
	 static class HashTable{
		Node[] indices;
		
		HashTable(int size){
			this.indices = new Node[size];
			for(int i = 0; i < indices.length; i ++)
				indices[i] = new Node();
		}
		
		int hashcode(int x){
			int result = (int) Math.pow(x*7, 2);
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
		Node next = null;
		int data;
		int key;
		
		Node(int k, int d){
			this.key  = k;
			this.data = d;
		}
		
		Node(){ }
				
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
			return -1;
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
			String w = "";
			for(int i = 0; i < words.size(); i ++){
				w += words.get(i);
			}
			return w;
		}	
	}
	
	/*Implementation of an ArrayList. Size of Array grows by 2 every time it's full*/
	static class mArrayList{
		int [] arr;
		int empty_index;
		
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
		
		T remove() throws Exception{
			T item;
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
	
	/*Implementation of Queue that only stores integers*/
	static class MyIntQueue{
		private static class QueueNode<T>{
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
		
		T pop() throws Exception{
			T item = null;
			if(top == null)
				throw new Exception();
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
	
	/*Implementation of a binary tree data structure that builds complete trees*/
	static class TreeNode<T>{
		T data;
		TreeNode<T> left;
		TreeNode<T> right;
		
		TreeNode (T d){
			data = d;
		}
		
		void add(T d) throws Exception{
			MyQueue<TreeNode<T>> queue = new MyQueue<TreeNode<T>>();
			TreeNode<T> node = new TreeNode<T>(d);
			boolean finished = false;
			
			if(left == null)
				left = node;
			else if(right == null)
				right = node;
			else{
				TreeNode<T> node2 = left;
				queue.add(right);
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
						queue.add(node2.left);
						queue.add(node2.right);
						node2 = queue.remove();
						}
					}
				}		
			}
		}
	
	/*Implementation of the Post Order Binary Tree Traversal*/
	static void postOrderTrav(TreeNode root){
		if(root != null){
			postOrderTrav(root.left);
			postOrderTrav(root.right);
			System.out.println(root.data);
		}
	}
	
	/*Implementation of the Pre-Order Binary Tree Traversal*/
	static void preOrderTrav(TreeNode root){
		if(root != null){
			System.out.println(root.data);
			preOrderTrav(root.left);
			preOrderTrav(root.right);
		}
	}
	
	/*Implementation of the In-Order Tree Traversal*/
	static void inOrderTrav(TreeNode root){
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
		int max_touches = arr.length; //will store the max number of nodes to be sorted
		boolean sorted = false;
		while(max_touches > 1){
			int count2 = 0;
			while(count2 < max_touches - 1){
				sorted = true;
				if(arr[count2] > arr[count2+1]){
					sorted = false;
					swap(arr, count2, count2 + 1); //swap values at current index and the adjacent index
				}
				count2 ++;
			}
			if(sorted)
				break;
			
			max_touches --;
		}
	}
	
	static void swap(int[] arr, int index1, int index2){
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}
	
	/*Implementaion of the Selection Sort Algorithm*/
	static void selectionSort(int[] arr){
		int max_touches = arr.length; //will store maximum num of nodes to be sorted
		boolean sorted = false; //checksif the array is already sorted
		
		while(max_touches > 1){
			int c2 = 0;
			int max_index = max_touches - 1;
			sorted = true; //we will assume the array is sorted, and update if wrong
			while(c2 < max_touches - 1){
				if(arr[c2] >= arr[max_index]){
					sorted = false;
					max_index = c2;
				}
				c2 ++;
			}
			if(sorted)
				break;
			
			swap(arr, max_index, max_touches -1); //swapp the largest so far with last of the unsorted part 
			max_touches --;
		}
	}
	
	/*Implementation of the Merge Sort Algorithm*/
	static void mergeSort(int[] arr){
		int[] helper = new int[arr.length];
		int low = 0;
		int high = arr.length -1;
		mergeSort(arr, helper, low, high);
	}
	
	static void mergeSort(int[] arr, int[] helper, int low, int high){
		if(low < high){
			int middle = (low + high)/2;
			mergeSort(arr, helper, low, middle);
			mergeSort(arr, helper, middle + 1, high);
			merge(arr, helper, low, middle, high);
		}
	}
	
	static void merge(int[] arr, int[] helper, int low, int middle, int high){
		for(int i = 0; i <= high; i ++){
			//copy elements in the array into a helper array that will temporarily store the current elements in array
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
		//copy remaining elements in the left side of array into the remaining places in their correct places
		for(int  i = 0; i <= remaining; i ++){
			arr[curr + i] = helper[helperLeft + i];
		}
	}
	
	/*Implementaion of the QuickSort Algorithm*/
	static void quicksort(int[] arr){
		int left = 0;
		int right = arr.length - 1;
		quicksort(arr, left, right);
	}
	
	static void quicksort(int[] arr, int left, int right){
		int index = partition(arr, left, right);
		if(left < index - 1)
			quicksort(arr, left, index -1);
		if(index < right)
			quicksort(arr, index, right);
	}
	
	static int partition(int[] arr, int left, int right){
		int mid = (left + right)/2;
		while(left <= right){
			while(arr[mid] > arr[left]) left++;
			while(arr[mid] < arr[right]) right --;
			if(left <= right){
				swap(arr, left, right);
				left ++;
				right --;
			}
		}
		return left;
	}
	
	/*Implementation of the RadixSort Algorithm*/
	static void radixsort(int[] arr, int passes) throws Exception{
		int i = 0; //used to keep track of passes
		while(i < passes){
			bucketsort(arr, i);
			i ++;
		}
	}
	
	static void bucketsort(int[] arr, int leastSigDig) throws Exception{
		MyIntQueue[] buckets = new MyIntQueue[10]; //each index corresponds to a digit 0-9
		boolean[] filled_queues = new boolean[10];
		int[] buckets_to_fill = new int[arr.length]; //keep track of next bucket with elements in it
		int next_bucket_counter = 0;
		for(int i = 0; i < buckets.length; i ++){
			buckets[i] = new MyIntQueue();
			filled_queues[i] = false;
		}
		
		for(int i = 0; i < arr.length; i ++){
			int num = arr[i];
			int sig_dig = digitAtIndex(num, leastSigDig);
			buckets[sig_dig].add(num);
			if(!filled_queues[sig_dig]){
				filled_queues[sig_dig] = true;
				buckets_to_fill[next_bucket_counter] = sig_dig;
				next_bucket_counter ++;
			}
		}
		
	
		 
		
		int count2 = 0;
		for(int i = 0; i < arr.length; i ++){
			if(buckets[buckets_to_fill[count2]].empty()){
				count2++;
				arr[i] = buckets[count2].remove();
			}	
		}
	}
	
	static int digitAtIndex(int num, int index){
		//assuming least significant digit starts at index 0
		int divider = (int) Math.pow(10,  index);
		int quotient = num / divider;
		return quotient%10;
	}
	
	/*Implementation of an Iterative Approach for the Binary Search Algorithm*/
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
		return -1; //error item not found
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
			return -1;
	}
}
