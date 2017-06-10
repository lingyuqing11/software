#include <stdlib.h>
#include <stdio.h>
#include <math.h>
#include <time.h>

//气球
typedef struct ball
{
	double x;       //圆心x坐标
	double y;		//圆心y坐标
	double r;		//圆半径
}Ball;

//用来储存已经放置的气球
typedef struct ballList
{
	struct ballList * next;
	Ball ball;
}BallList;

void insert(Ball ball);						//插入气球
double distance(Ball b1, Ball b2);          //判断气球之间的距离
//double abs(double num);					    //取绝对值
int judge(Ball b);							//判断新加入的气球是否符合规则
void putBall();								//改变气球的初始位置，求的满足条件的气球
void putPoint(double x, double y);			//放置钉子

BallList *head = NULL;
double step = 0.01;							//改变气球位置的最小单位
int num = 0;                                //放置气球的个数


int main()
{
	int ball_num, point_num, i;
	printf("请输入空间内所要放置的气球数量: ");
	scanf_s("%d", &ball_num);
	printf("请输入空间内钉子的数量: ");
	scanf_s("%d", &point_num);
	printf("\n");
	srand(time(NULL));
	for (i = 0; i < point_num; i++)
	{
		//printf("请输入第%d个钉子的横纵坐标(其间用空格隔开)：", i + 1);
		double x, y;
		//scanf_s("%lf %lf", &x, &y);
		x = -1+(rand() % 200 + 1)*0.01;
		y = -1 + (rand() % 200 + 1)*0.01;
		printf("第%d个钉子的坐标（%lf,%lf）\n", i + 1, x, y);
		putPoint(x, y);
	}
	printf("\n球编号\t x坐标\t y坐标\t 半径\t \n");
	for (i = 0; i < ball_num; i++)
	{
		putBall();
	}
	

	system("pause");
	return 0;
}

void putPoint(double x, double y)
{
	Ball ball = { x, y, 0 };   //把钉子当做半径为0的圆
	insert(ball);
}

void insert(Ball ball)  //把圆放入堆栈
{
	ballList * newBall = (ballList *)malloc(sizeof(ballList));
	newBall->ball = ball;
	newBall->next = head;
	head = newBall;
}

void putBall()
{
	Ball ball = { -1 + step, -1 + step, 0 };
	Ball maxBall = ball;
	int i, j;
	for (i = 0; ball.x < 1; ++i)
	{
		ball.x += step;
		ball.y = -1 + step;
		for (j = 0; ball.y < 1; ++j)
		{
			ball.y += step;
			ball.r = 0;
			double rstep = 0.1;
			while (rstep > 0.00001)
			{
				if (ball.r > maxBall.r)
				{
					maxBall = ball;
				}
				ball.r += rstep;
				if (!judge(ball))
				{
					ball.r -= rstep;
					rstep /= 10;
				}
			}
		}
	}
	if (judge(maxBall))
	{
		insert(maxBall);
		num++;
		printf("%d\t %.3lf\t %.3lf\t %.3lf\t\n", num, maxBall.x, maxBall.y, maxBall.r);
	}

	
}

int judge(Ball b)
{
	if ((abs(b.x) + b.r) > 1 || (abs(b.y) + b.r) > 1)
	{
		return false;
	}
	ballList *tmp = head;
	while (tmp)
	{
		Ball ball = tmp->ball;
		if (distance(b, ball) < b.r + ball.r)
		{
			return false;
		}
		tmp = tmp->next;
	}
	return true;
}

double distance(Ball b1, Ball b2)
{
	double x1 = b1.x;
	double y1 = b1.y;
	double x2 = b2.x;
	double y2 = b2.y;
	return pow((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2), 0.5);
}