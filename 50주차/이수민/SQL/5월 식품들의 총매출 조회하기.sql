-- 코드를 입력하세요
SELECT p.product_id, p.product_name, SUM(o.amount * p.price) as pricesum
from FOOD_PRODUCT p
join FOOD_ORDER o on p.product_id = o.product_id
where o.PRODUCE_DATE >='2022-05.01' and o.PRODUCE_DATE <='2022-05.31'
order by pricesum desc, p.product_id asc



# 생산일자가 2022년 5월인 식품들의 식품 ID,식품이름, 총매출을 조회
# 총매출 기준으로 내림차순 정렬, 총매출 같다면 식품 ID
