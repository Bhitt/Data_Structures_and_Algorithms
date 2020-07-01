public class The_Sieve_of_Eratosthenes {
	/*
		The sieve of eratosthenes is a highly efficient way to generate a list of primes.
		It works by recognizing that all non-prime numbers are divisible by a prime number.
	*/

	public static void main(String[] args) {
		int num = 50;
		if(args.length > 0) num = Integer.parseInt(args[0]);
		boolean[] primes = sieveOfEratosthenes(num);
		for (int i = 0; i < primes.length; i++) {
			if (primes[i]) {
				System.out.println(i);
			}
		}
	}

	public static boolean[] sieveOfEratosthenes(int max){
		boolean[] flags = new boolean[max+1];
		int count = 0;

		init(flags); //Set all flags to true other than 0 and 1
		int prime = 2;

		while(prime <= Math.sqrt(max)){
			/* Cross off remaining multiples of prime */
			crossOff(flags, prime);

			/* Find the next value which is true */
			prime = getNextPrime(flags, prime);
		}

		return flags;
	}

	public static void crossOff(boolean[] flags, int prime){
		/*
			Cross off remaining multiples of prime. We can start with (prime*prime),
			because if we have a k* prime, where k < prime, this value would have 
			already been crossed off in a prior iteration.
		*/
		for(int i = prime * prime; i < flags.length;i+=prime){
			flags[i] = false;
		}
	}

	public static int getNextPrime(boolean[] flags, int prime){
		int next = prime + 1;
		while(next < flags.length && !flags[next]){
			next++;
		}
		return next;
	}

	public static void init(boolean[] flags) {
		flags[0] = false;
		flags[1] = false;
		for (int i = 2; i < flags.length; i++) {
			flags[i] = true;
		}
	}

	/*
		Note: One optimization would be to use only odd numbers in the flags array
	*/

}