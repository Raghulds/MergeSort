package org.example.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MergeSorterSingleThread {

    private List<Integer> arrayToSort;

    public MergeSorterSingleThread(List<Integer> arrayToSort){
        this.arrayToSort = arrayToSort;
    }

    // recursive
    public List<Integer> merge() throws ExecutionException, InterruptedException {
        if(arrayToSort.size() <= 1){
            return arrayToSort;
        }

        int mid = arrayToSort.size() / 2; // 2 -> 1, 3 -> 1

        List<Integer> leftArray = new ArrayList<>();

        for(int i=0; i < mid; i++){
            leftArray.add(arrayToSort.get(i));
        }

        List<Integer> rightArray = new ArrayList<>();
        for(int i=mid; i < arrayToSort.size(); i++){
            rightArray.add(arrayToSort.get(i));
        }

        MergeSorterSingleThread leftMergeSorter = new MergeSorterSingleThread(leftArray);
        MergeSorterSingleThread rightMergeSorter = new MergeSorterSingleThread(rightArray);

//        List<Integer> leftSortedArrayFuture = leftMergeSorter.merge();
//        List<Integer> rightSortedArrayFuture = rightMergeSorter.merge();

        // code will not go to the next line till I have the sorted array
        List<Integer> leftSortedArray = leftMergeSorter.merge();
        List<Integer> rightSortedArray = rightMergeSorter.merge();

        // Merge 2 sorted arrays
        List<Integer> sortedArray = new ArrayList<>();

        int i=0;
        int j=0;

        while(i < leftSortedArray.size() && j < rightSortedArray.size()){
            if(leftSortedArray.get(i) < rightSortedArray.get(j)){
                sortedArray.add(leftSortedArray.get(i));
                ++i;
            }
            else{
                sortedArray.add(rightSortedArray.get(j));
                ++j;
            }
        }

        while(i < leftSortedArray.size()){
            sortedArray.add(leftSortedArray.get(i));
            i++;
        }

        while(j < rightSortedArray.size()){
            sortedArray.add(rightSortedArray.get(j));
            j++;
        }

        return sortedArray;
    }
}
