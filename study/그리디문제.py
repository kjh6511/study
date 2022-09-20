#### 그리디 문제
# 그리디 
# 현재 상황에서 가장 좋아 보이는 것만 선택하는 알고리즘
# 대표적인 문제 : 거스름돈(거슬러야할 동전의 최소 개수), 1이될때까지(1빼기 혹은 K나누기, 나누기가 빠르게 값을 줄임)
# 다익스트라 최단 경로 알고리즘 : 특정 노드까지 최단 거리를 계산한 다음 메모리에 저장
# 크루스칼 알고리즘 : 대표적인 최소 신장 트리 알고리즘 (신장 트리 : 하나의 그래프가 있을 때 모든 노드를 포함하면서 사이클이 존재하지 않는 부분 그래프를 의미)
import time

def study_01():
    ### 모험가 길드 
    
    
    n = int(input())
    data = list(map(int, input().split()))
    result = 0
    
    count = 0

    data.sort()
    
    for i in data:
        count += 1
        if i <= count:
            result += 1
            count = 0
    
    print(result)

def study_02():
    ## 곱하기 혹은 더하기
    ## 0이거나 1이면 더하기 
    
    n = input()
    result = int(n[0]) #첫값 저장
    
    for i in range(1, len(n)): #첫값 다음 부터 끝까지
        val = int(n[i])
        if val <= 1 or result <= 1: #0,1이면 더하기 
            result += val    
        else:
            result *= val

    print(result)

def study_03():
    ##문자열 뒤집기 
    m = input()
    n = []

    for i in range(len(m)):
        n.append(int(m[i]))

    count = 0 #변환 횟수
    while True:
        zero = []
        one = []
        check = n[0] #이전값
        countNum = 1 #연속숫자가 있는지
        n.append(3) #마지막숫자확인용
        for i in range(1, len(n)):
            if  n[i] != check and countNum > 1: #연속 숫자이며 앞수와 다르면 카운트
                if check == 0:
                    zero.append([i for i in range(i-countNum, i)])
                else:
                    one.append([i for i in range(i-countNum, i)])
                countNum = 1 #연속 숫자 초기화 
            elif n[i] == check: #같으면 연속 숫자
                countNum += 1
            check = n[i]  
        del n[-1] #마지막 요소 제거 

        def chageNum(n, val, changeList):
            for i in changeList:
                for j in i:
                    n[j] = val
        print('n :', n)
        if max(len(zero), len(one)) != 0: #정지처리
            if len(zero) == 0:
                if len(one[0]) == len(n):
                    break
            elif len(one) == 0:
                if len(zero[0]) == len(n):
                    break
            
            count += 1
            if len(zero) >= len(one): #0변환이 많다
                chageNum(n,1,zero)
            else:
                chageNum(n,0,one)  
        else:
            break
    print(count)    
    
    
    ##풀이
    data = m
    count0 = 0 #전부 0으로 바꾸는 경우
    count1 = 0 #전부 1로 바꾸는 경우
    
    if data[0] == '1':
        count0 += 1
    else:
        count1 += 1
    
    #두번째 원소부터 모든 원소를 확인하며
    for i in range(1, len(data)-1):
        if data[i] != data[i+1]:
            #다음 수에서 1로 바뀌는 경우
            if data[i+1] == '1':
                count0 += 1
            #다음 수 에서 0으로 바뀌는 경우
            else:
                count1 += 1
    print(min(count0, count1))

def study_04():
    n = int(input())
    data = list(map(int,input().split()))
    
    data.sort()
    
    target = 1
    for i in data:
        
        if target < i:
            break
        
        target += i
    
    print(target)

def study_05():
    
    n, m = map(int, input().split())
    data = list(map(int, input().split()))
    
    count = 0
    
    for i in range(n):
        for j in range(i+1, n):
            if data[i] != data[j]:
                count += 1
    
    print(count)
    
    
    #### 무게 풀이법
    
    #1~10까지의 무게를 담을 수 있는 리스트
    array = [0] * 11
    for x in data:
        array[x] += 1
        
    result = 0
    #1부터 m까지의 각 무게에 대하여 처리
    for i in range(1,m+1):
        n -= array[i] #무게가 i인 볼링공의 개수(A가 선택할 수 있는 개수) 제외
        result += array[i] * n #B가 선택하는 경우의 수와 곱하기
    print(result)

def study_06():
    start = time.time()
    #무지의 먹방
    def solution(food_times, k):
        
        #음식이 있는 값 찾기 
        def find_food(foods, checkNum, first):
            turn = 0
            if foods[checkNum] > 0:
                return checkNum
            else:
                checkNum += 1
                if checkNum == len(foods):
                    turn += 1
                    checkNum = 0
                if turn == 1 and checkNum == first:
                    return -1
            return find_food(foods, checkNum, first)
        
        answer = 0
        
        check = 0
        for i in range(k):
            find = find_food(food_times, check, check)
            if find != -1:
                food_times[find] -= 1
                check = find + 1
                if check == len(food_times):
                    check = 0
            else:
                check = -2
                break
        answer = check+1
        return answer    
    
    data = [6,8,4]
    n = 15
    print(solution(data, n))
    
    end = time.time()
    print(f"{end - start:.5f} sec")
    
    ## 책 풀이
    ## 탐욕적 접근 -> 시간이 적게 걸리는 음식부터 제거
    start02 = time.time()
    
    import heapq
    
    def solution_02(food_times, k):
        #전체 음식을 먹는 시간보다 k가 크거나 같다면 -1
        if sum(food_times) <= k:
            return -1
        
        #시간이 작은 음식부터 빼야 하므로 우선순위 큐를 이용
        q=[]
        for i in range(len(food_times)):
            #(음식 시간, 음식 번호) 형태로 우선순위 큐에 삽입
            heapq.heappush(q, (food_times[i], i+1))
            
        sum_value = 0 #먹기 위해 사용한 시간
        previous = 0 #직전에 다 먹은 음식 시간
        
        length = len(food_times) #남음 음식 개수
        
        #sum_value + (현재의 음식 시간 - 이전 음식 시간) * 현재 음식 개수와 k 비교
        while sum_value + ((q[0][0] - previous ) * length) <= k:
            now = heapq.heappop(q)[0]
            sum_value += (now - previous) * length
            length -= 1 #다먹은 음식 제외
            previous = now #이전음식 시간 재설정
        
        #남은 음식 중에서 몇번째 음식인지 확인하여 출력
        result = sorted(q, key=lambda x: x[1]) #음식의 번호 기준을 정렬
        return result[(k-sum_value) % length][1]
    
    data = [6,8,4]
    print(solution_02(data, 15))
    end02 = time.time()
    print(f"{end02 - start02:.5f} sec")
####### 실행용
study_06()
