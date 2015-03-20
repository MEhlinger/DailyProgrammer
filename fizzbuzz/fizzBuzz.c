#include <stdio.h>
#include <stdbool.h>

int main() {
	const int MAX = 100;
	int i;
	bool divisible;

	for (i = 1; i <= MAX; i++) {
		divisible = false;

		if (i % 3 == 0) {
			printf ("fizz");
			divisible = true;
		}
		if (i % 5 == 0) {
			printf ("buzz");
			divisible = true;
		}
		if (!(divisible)) {
			printf("%d", i);
		}
		
		printf("\n");

	}
	return 0;
}