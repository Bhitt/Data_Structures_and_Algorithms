public class Useful_Bit_Manip {
	/*
		These are some useful operations for bit manipulation

		GetBit
		SetBit
		ClearBit
		UpdateBit
	*/

	public static void main(String[] args) {
		//create test data
		int number = 38;
		int i = 2;
		System.out.println("Number: "+Integer.toBinaryString(number));
		System.out.println("i: "+i);
		boolean getBit = getBit(number, i);
		System.out.println("Get Bit at i: "+getBit);
		number = clearBit(number, i);
		System.out.println("Clear Bit at i: "+Integer.toBinaryString(number));
		number = updateBit(number, i, true);
		System.out.println("Update Bit at i with true: "+Integer.toBinaryString(number));
		number = setBit(number, i);
		System.out.println("Set Bit at i: "+Integer.toBinaryString(number));
		int num2 = clearBitsMSBthroughI(number, i);
		System.out.println("Clear Bits through i: "+Integer.toBinaryString(num2));
		int num3 = clearBitsIthrough0(number, i);
		System.out.println("Clear Bits i through 0: "+Integer.toBinaryString(num3));
	}


	/*
		Get Bit :  This method shifts 1 over by i bits, creating a
		value that looks like 00010000. By performing an AND with
		numm, we clear all bits other than the bit at bit i. Finally,
		we compare that to 0. If that new value is not zero, then the
		bit at i must have been a 1. Otherwise, bit i is a 0.
	*/
	public static boolean getBit(int num, int i){
		return ((num & (1 << i)) != 0);
	}

	/*
		Set Bit: This method shifts 1 over by i bits, creating a value
		like 00010000. By performing an OR with num, only the value at
		bit i will change. All other bits of the mask are zero and will
		not affect num. (If bit i is 0, set it to 1, or leave it at 1 
		otherwise).
	*/
	public static int setBit(int num, int i){
		return num | (1 << i);
	}

	/*
		Clear Bit : This method creates a number like 11101111 by creating
		the reverse of it (00010000) and negating it. Then, we perform an
		AND with num. This will clear the ith bit and leave the number
		unchanged. (IF bit i is 0 leave it, otherwise change it to 0).
	*/
	public static int clearBit(int num, int i){
		int mask = ~(1 << i);
		return num & mask;
	}

	/* 
		Clear Bits from Most Significant Bit through i (inclusive) : Create a
		mask with a 1 at the ith bit (1 << i). Then, we subtract 1 from it,
		giving us a sequence of 0s followed by i 1s. We then AND our number
		with this mask to leave just the last i bits.
	*/
	public static int clearBitsMSBthroughI(int num, int i){
		int mask = (1 << i) - 1;
		return num & mask;
	}

	/*
		Clear Bits i through 0 (inclusive) : Take a sequence of all 1s (which
		is -1) and shift it left by i + 1 bits. This gives us a sequence of 
		1s (in the most significant bits) followed by i 0 bits.
	*/
	public static int clearBitsIthrough0(int num, int i){
		int mask = (-1 << (i +1));
		return num & mask;
	}

	/*
		Update Bit: Clear the bit at position i by using a mask that looks
		like 11101111. Then, we shift the intended value, v, left by i bits.
		This will create a number with bit i equal to v and all other bits
		equal to 0. Finally, we OR these two numbers, updating the ith bit
		if v is 1 and leaving it as 0 otherwise.
	*/
	public static int updateBit(int num, int i, boolean bitIs1){
		int value = bitIs1 ? 1 : 0;
		int mask = ~(1 << i);
		return (num & mask) | (value << i);
	}
}