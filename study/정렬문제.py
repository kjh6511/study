#정렬문제
#특정 기준에 따라서 정렬

#선택정렬 : 간단 -> 가장 작은 데이터를 선택해서 정렬되지 않는 앞쪽 데이터와 위치를 바꿈
#삽입정렬 : 데이터가 거의 정렬되어 있을때는 빠름 -> 앞에서부터 하나씩 확이하며 적절한 위치에 삽입
#퀵정렬 : 대부분의 경우 가장 적합, 빠름 -> 기준 데이터를 설정하고 기준보다 큰 데이터와 작은 데이터의 위치를 바꿈
#계수정렬 : 데이터의 크기가 한정되어 있는 경우에만 사용, 매우빠름 -> 값들을 카운트 하는 방법

#파이썬 라이브러리를 활용하는게 빠름
#이진 탐색의경우 정렬이 되어 있을때만 사용 가능
#크루스칼 알고리즘의 경우 간선정보를 정렬하는 과정이 필요

def study_01():
    ###국영수
    #국어 점수가 감소하는 순
    #국어 점수가 같으면 영어 점수가 증가하는 순
    #국어 점수와 영어 점수가 같으면 수학점수가 감소하는 순
    #모든 점수가 같으면 이름이 사전 순으로 증가하는 순
    
    # n = int(input())
    
    data = [("Jenkyu", 50, 60, 100), ("SangKeun", 80, 60, 50), ("Sunyoung", 80, 70, 100), ("Soong", 50, 60, 90)
            ,("Haebin", 50, 60, 100), ("Kangsoo", 60, 80, 100), ("Donghyuk", 80, 60, 100),("Sei", 70, 70, 70)
            ,("Wonseob", 70, 70, 90), ("Sanghyun", 70, 70, 80), ("nsj", 80, 80, 80), ("Taewhan", 50, 60, 90)]
    
    # for _ in range(n):
    #     a, b, c, d = input().split()
    #     data.append((a, int(b), int(c), int(d)))
           
    data = sorted(data, key = lambda x: (-x[1], x[2], -x[3], x[0]))
    
    for i in data:
        print(i[0])

    #책풀이
    data.sort(key = lambda x : (-x[1], x[2], -x[3], x[0]))

def study_02():
    ### 안테나
    n = int(input())
    data = list(map(int, input().split()))
    
    sum_list = [ sum(data) for _ in range(max(data)+1) ]
    
    for i in data:
        sum_data= 0
        for j in data:
            sum_data += (abs(i-j))
        sum_list[i] = sum_data   
    
    min_data = min(sum_list)
    print(sum_list.index(min_data))
    
    ##책풀이
    #중간값일때 최소가 됨 
    data.sort()
    print(data[(n-1)//2])

def study_03():
    ###실패율
    
    #실패율 = 막스테이지,클리어x / 막스테이지,클리어
    n = 5
    stage = [2,1,2,6,2,4,3,3]
    stage_result = []   
    
    for i in range(1, n+1):
        visited = 0 #스테이지 도전자
        none_clear = 0 #아직 클리어 못한 유저
        for j in stage:
            if i <= j: #도전자
                visited += 1
            if i == j: #아직 클리어 못함
                none_clear += 1
            
        #0처리
        if visited != 0 and none_clear != 0:
            stage_result.append((i, none_clear/visited))
        elif visited != 0 and none_clear == 0:
            stage_result.append((i, 0))
    
    stage_result.sort(key = lambda x: (-x[1], x[0]))
    
    result = []
    for i in stage_result:
        result.append(i[0])
    print(result)    
    
    ###책풀이
    def solution(N, stages):
        answer = []
        length = len(stages)
        
        for i in range(1, N+1):
            count = stages.count(i)

            #실패율 계산
            if length == 0:
                fail = 0
            else:
                fail = count/length
        
            #(스테이지번호, 실패율) 삽입
            answer.append((i,fail))
            length -= count

        answer = sorted(answer, key=lambda t : t[1], reverse=True)
        
        answer = [i[0] for i in answer]
        return answer
    
    print(solution(n,stage))
    
def study_04():
    ###카드 정렬하기
    import queue 
    data = [10,20,40,50]
    n = len(data)
    
    q = queue.PriorityQueue() ## 우선순위큐 (heapq이 더빠름)
    for i in data:
        q.put(i)
    
    result = 0
    while q:
        one = q.get()
        if q.empty():
            result = one
            break
        two = q.get()
        q.put(one+two)
    
    print(result)
    
    ##책풀이
    import heapq
    
    heap = []
    for i in data:
        heapq.heappush(heap, i)

    r= 0
    
    while len(heap) != 1:
        one = heapq.heappop(heap)
        two = heapq.heappop(heap)
        sum_value = one + two
        r += sum_value
        heapq.heappush(heap, sum_value)
    
    print(r)
    
        
    
    

study_04()