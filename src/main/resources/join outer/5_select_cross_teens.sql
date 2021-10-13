select tb.name as "Boy", tg.name as "Girl" from teens tb cross join teens tg
where tb.gender != tg.gender and tb.gender like '%m%';