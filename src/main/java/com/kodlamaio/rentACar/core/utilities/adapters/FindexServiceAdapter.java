package com.kodlamaio.rentACar.core.utilities.adapters;

import java.util.HashMap;
import java.util.Random;
import org.springframework.stereotype.Service;
@Service
public class FindexServiceAdapter {
	Random random = new Random();
	HashMap<String, Integer> findexScore;
	
	public int CheckFindexScore(String tcNo) {
	    findexScore = new HashMap<String,Integer>();
		int score = random.nextInt(1899)+1;
		findexScore.put(tcNo,score);
		System.out.println(score);
		return score;
		
	}
}
