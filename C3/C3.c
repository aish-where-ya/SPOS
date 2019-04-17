#include<stdlib.h>
#include<unistd.h>
#include<stdio.h>
#include<sys/wait.h>
int main()
{
	int choice;
	char* args1[] = {"ps",NULL};
	char* args3[] = {"join", "file1","file2",NULL};
	char* args4[] = {"ls", NULL};
	int pid,status,id;
	printf("Enter choice \n1. ps \n2. fork \n3. join \n4. exec \n5. wait \n6. exit");
	scanf("%d", &choice);
	switch(choice)
	{
		case 1:
		execvp(args1[0],args1);
		break;
		
		case 2:
		printf("Fork\n");
		pid = fork();
		if(pid ==0)
		{
			sleep(5);
			printf("Child process %d \n", getpid());
			sleep(5);
			printf("Parent process %d \n ", getppid());
		}
		else if(pid>0)
		{
			sleep(5);
			printf("Child process %d \n", getpid());
			sleep(5);
			printf("Parent process %d \n ", getppid());
		}
		break;
		
		case 3:
		execvp(args3[0], args3);
		break;
		
		case 4:
		execvp(args4[0], args4);
		break;
		
		case 5:
		printf("Wait");
		pid = fork();
		if(pid==0)
		{
			printf("Child process %d \n", getpid());
			printf("Parent process %d \n ", getppid());
			id = wait(&status);
			printf("Process terminated %d \n", id);
		}
		else if(pid>0)
		{
			printf("Child process %d \n", getpid());
			printf("Parent process %d \n ", getppid());
			id = wait(&status);
			printf("Process terminated %d \n", id);
		}
		break;
		
		case 6:
		break;
		
		default:
		printf("wrong choice");
	}
	return 0;
}
