####다이나믹 프로그래밍
#한 번 계산한 문제는 다시 계산하지 않도록 하는 알고리즘
#탑다운, 보텀업 2가지 방법

def study_01():
    #피보나치
    
    #아래 방법의 경우 숫자가 꺼질때 마다 같은 반복 트리가 있어 시간이 걸림
    def fibo_01(x):
        if x == 1 or x ==2:
            return 1
        return fibo_01(x - 1) + fibo_01(x-2)
    print(fibo_01(4))
    
    #메모이제이션 : 한번구한 결과를 메모리 공간에 메모해두고 같은 식이면 결과 호출 (= 캐싱)
    #피보나치 수열 소스코드 (재귀적)_ 탑다운
    d = [0] * 100 # 메모이제이션을 위한 초기화
    def fibo_02(x):
        if x == 1 or x == 2: #종료 조건 (1혹은 2일 때 1 반환)
            return 1
        if d[x] != 0: #계산한 적 있는 문제라면 반환
            return d[x] 
        d[x] = fibo_02(x-1) + fibo_02(x-2)
        return d[x]
    print(fibo_02(99))
    
    #탑다운 방식(하향식): 큰 문제를 해결하기 위해 작은 문제를 호출한다, 메모제이션
    #보텀업 방식(상향식): 단순히 반복문을 이용한 경우 작운 문제부터 차근차근 답을 도출한다
    
    #피보나치(반복적)_ 보텀업 방식
    d = [0] * 100
    d[1] = 1
    d[2] = 1
    n = 99
    
    for i in range(3, n+1):
        d[i] = d[i-1] + d[i-2]
    
    print(d[n])    

def study_02():
    #1로만들기
    #다이나믹문제, 보텀업 방식, DP문제, 점화식: 전의 결과를 이용 
    # 10의 경우 10 -> 9 -> 3 -> 1
    # 9의 경우 9 -> 3-> 1
    # 3의 경우 3 -> 1
    # 10은 9의 계산값을 9는 3의 계산값을 이용하기때문에 점화식
    
    x = int(input())
    d = [0] * 30001 # 리스트는 0부터 시작하기때문에 1을 더함 
    
    for i in range(2, x+1):
        d[i] = d[i-1] + 1
        if i % 2 == 0:
            d[i] = min(d[i], d[i//2]+1)
        if i % 3 == 0:
            d[i] = min(d[i], d[i//3]+1)
        if i % 5 == 0:
            d[i] = min(d[i], d[i//5]+1)
    print(d[x])

def study_03():
    ## 개미전사
    #보텀업
    n = int(input())
    array = list(map(int, input().split()))
    d = [0] * 100
    
    d[0] = array[0]
    d[1] = max(array[0], array[1])
    for i in range(2,n):
        d[i] = max(d[i-1], d[i-2]+array[i])
    print(d[n-1])    


def study_04():
    ###바닥공사
    # 1<= n <= 1000
    n = int(input())
    d = [0] * 1001

    d[1] = 1
    d[2] = 3
    for i in range(3, n+1):
        d[i] = (d[i-1] + 2 * d[i-2]) % 796796
    print(d[n])

def study_05():
    #효율적인 화폐 구성
    n,m = map(int, input().split())
    array = []
    for i in range(n):
        array.append(int(input()))

    d = [10001] * (m+1)
    #다이나믹 프로그래밍 보텀업
    d[0] = 0
    for i in range(n): # i = 0~n-1까지 n번 반복
        for j in range(array[i], m+1):
            print("i:", i, " j: ", j, "j - array[i] :", j - array[i], "d :",d[j - array[i]] )
            if d[j - array[i]] != 10001: #(i-k)원을 만드는 방법이 존재하는 경우
                d[j] = min(d[j], d[j-array[i]]+1)
    
    if d[m] == 10001: #최종적으로 m원을 만드는 방법이 없는 경우
        print(-1)
    else:
        print(d[m])

####### 실행용
study_02()
