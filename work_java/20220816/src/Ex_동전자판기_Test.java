import java.util.Scanner;

public class Ex_동전자판기_Test {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int targetMoney = sc.nextInt();

		int[] units = { 500, 100, 50, 10, 5, 1 };
		int[] counts = { sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt() };

		int totalMoney = 0;
		for (int i = 0; i < units.length; i++) {
			totalMoney += units[i] * counts[i];
		} // 보유하고 있는 모든 동전들로 만들 수 있는 금액 계산

		int remainMoney = totalMoney - targetMoney; // 갖고 있는 돈에서 음료수 값을 지불하고 남은 나머지 금액 
		
		// 음료수 값을 지불하는 데 동전을 최대로 사용하려면, 지불하고 남은 금액의 동전수를 최소로 하면 됨.
		int sum = 0, maxCnt, availCnt;
		for(int i=0; i<units.length; i++) { // 가장 큰 화폐 단위부터 많이 사용하도록
			maxCnt = remainMoney/units[i]; // 해당 금액을 i 화폐 단위로 가장 많이 쓸 때의 개수 
			availCnt = maxCnt <= counts[i]? maxCnt : counts[i]; // 보유 개수가 max보다 크면 다 쓸 수 있고
			// 아니면 보유하고 있는 것밖에 쓸 수 없음 
			
			counts[i] -= availCnt; // 사용하고 남은 동전의 개수 
			remainMoney -= availCnt*units[i]; // 실제 사용 * 화폐 단위 빼주기 -> 금액 계속 줄여주기 
			
			sum += counts[i]; // 사용되고 남은 동전을 지불하기 위해 사용될 동전 수 
		}
		
		System.out.println(sum); // 음료수 값을 지불하기 위해 사용된 동전 수 
		for(int i=0; i<counts.length; i++) {
			System.out.print(counts[i] + " ");
		}
		System.out.println();
		
	}

}
