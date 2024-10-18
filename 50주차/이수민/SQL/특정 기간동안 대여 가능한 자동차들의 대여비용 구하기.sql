# 자동차 종류가 '세단' 또는 'SUV' 인 자동차 중 
#2022년 11월 1일부터 2022년 11월 30일까지 대여 가능하고 
#30일간의 대여 금액이 50만원 이상 200만원 미만인 자동차에 대해서 
#자동차 ID, 자동차 종류, 대여 금액(컬럼명: FEE)

SELECT c.car_id, c.car_type, Round(c.daily_fee*30*(100-DISCOUNT_RATE) / 100) as FEE
from CAR_RENTAL_COMPANY_CAR c
join CAR_RENTAL_COMPANY_RENTAL_HISTORY h on c.car_id = h.car_id
join CAR_RENTAL_COMPANY_DISCOUNT_PLAN p on c.car_type = p.car_type
where c.car_id not in(
    select car_id
    from CAR_RENTAL_COMPANY_RENTAL_HISTORY 
    where START_DATE<'2022-12-01'and END_DATE>'2022-11-01'
)
and DURATION_TYPE = '30일 이상' and c.car_type in('세단', 'SUV')
group by car_id
having FEE>=500000 and FEE<2000000
order by FEE desc, c.car_type, c.car_id desc
