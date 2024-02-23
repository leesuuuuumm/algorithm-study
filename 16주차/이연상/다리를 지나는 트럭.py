# [PRG] 다리를 지나는 트럭  

def solution(bridge_length, weight, truck_weights):
    answer=0
    bridge=[0 for _ in range(bridge_length)]
    s=0
    while bridge:
        answer+=1
        tmp=bridge.pop(0)
        s-=tmp
        if truck_weights:
            if truck_weights[0]+s<=weight:
                tmp=truck_weights.pop(0)
                bridge.append(tmp)
                s+=tmp
            else:
                bridge.append(0)
    return answer