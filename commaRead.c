/*
* Comma-Delimted Text Reader
* by Marshall Ehlinger
*
* For practice only: 
* Reads  + prints .txt of comma-seperated values < 60 characters long
*/

#include <stdio.h>
#include <string.h>

int main() {
	FILE *filePointer = fopen("data.txt", "r");
	const char delim[2] = ",";
	char *token;
	char buffer[60];
	int bufferLen = sizeof buffer / sizeof buffer[0];

	if (filePointer != NULL) {
		while (fgets(buffer, bufferLen, filePointer) != NULL) {
			token = strtok(buffer, delim);
			 while (token != NULL) {
				printf("%s\n", token);
				token = strtok(NULL, delim);
			}
		}
		fclose(filePointer);
	} else {
		perror("data.txt");
	}
	return 0;
}