####그리디 : 현재 상황에서 가장 좋아 보이는 것만 선택하는 알고리즘

###거스름돈

def study_01():
    n = 1260
    count = 0
    coin_types = [500,100,50,10]

    for coin in coin_types:
        count += (n//coin)  # //은 정수만, 소수점이하 생략
        n %= coin
    print(count)    

###큰수의 법칙 (N배열에서 M번 더하고 연속K번 초과할 수 없을때 가장 큰 수, K <= M)
def study_02():
    #n:배열길이, m:총더한수, k:연속가능수
    #n,m,k를 공백으로 구분하여 입력받기
    n, m, k = map(int, input().split())
    #n개의 수를 공백으로 구분하여 입력받기
    data = list(map(int, input().split()))
    data.sort() #정렬
    first = data[n-1] # 가장 큰 수
    second = data[n-2] # 가장 작은 수

    result = 0

    #sol_01
    m_count = m
    while True:
        for i in range(k): #가장 큰수를 k번 더하기 
            if m_count == 0:
                break #0이면 탈출
            result += first
            m_count -= 1 #더할때마다 1빼기
        if m_count == 0:
            break
        result += second
        m_count -= 1    
    print("sol_01 : ",result)

    #sol_02
    count = int(m / (k+1)) * k #가장 큰수가 더해지는 횟수 계산
    count += m % (k+1) #반복후 남는수만큰 횟수 더함
    result = 0
    result += (count) * first
    result += (m-count) * second
    print("sol_02 : ",result)

#숫자카드게임 
def study_03():
    # 1.행열 입력 nxm 2.각 행들중에서 가장 낮은 수의 카드를 뽑아야함 3. 그중에서 가장 큰수가 이김
    n = 0 #행
    m = 0 #열
    
    # min() 함수 이용
    n, m = map(int, input().split())
    result = 0
    for i in range(n):#한줄씩
        data = list(map(int, input().split()))
        min_value = min(data) #가장 작은수 찾기
        result = max(result, min_value) #가장 작은수 중, 가장 큰수 찾기
    print(result)

    #2중 반복문 이용
    for i in range(n):
        data = list(map(int, input().split()))
        min_value = 10001
        for a in data:
            min_value = min(min_value, a)
        result = max(result,min_value)
        
    print(result)  

#나눌수 있을 때까지 1빼고 마지막 1이 남을때 계산 횟수는                                                                                                                                     
def study_04():
    #sol_01
    n, k = map(int, input().split())
    result = 0
    while n >= k:
        while n % k != 0: #n이 k로 나누어지지 않는다면 n에서 1빼기
            n -= .1
            result += 1
        n //= k
        result += 1
    while n > 1:
        n -= 1
        result += 1
    print(result)   
    
    #sol_02
    n, k = map(int, input().split())
    result = 0
    while True:
        target = (n//k) * k #정수로 나눠지는 수 중 큰수
        result += (n - target) # 마지막 나누기 빼기
        n = target 
        if n < k:
            break
        result += 1
        n //= k
    result += (n-1) #마지막 남은수에 대하여 1빼기
    print(result)    
        
     
####### 실행용
study_04()
