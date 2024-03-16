# jpa-entity-manager

## 🚀 1단계 - 엔터티 매핑 (EntityPersister)

### 요구사항 1 - 엔터티의 데이터베이스 매핑, 쿼리 생성 및 실행

1. EntityPersister: insert 기능 구현
2. DMLGenerator: update 쿼리 생성 구현
3. EntityPersister: update 기능 구현
4. EntityPersister: delete 기능 구현

### 요구사항 2 - EntityManager 의 책임 줄여주기

1. EntityManager의 insert, delete 책임 줄여주기

## 🚀 2단계 - 엔터티 초기화 (EntityLoader)

### 요구사항 1 - RowMapper 리팩터링

### 요구사항 2 - EntityManager 의 책임 줄여주기

## 🚀 3단계 - First Level Cache, Dirty Check

### 요구사항1 - PersistenceContext 구현체를 만들어 보고 1차 캐싱을 적용해보자

1. entitiesByKey 지연 초기화
2. entitiesByKey 에서 id로 엔티티 조회할 수 있도록 1차 캐싱 적용

### 요구사항2 - snapshot 만들기

1. entitySnapshotsByKey 지연 초기화
2. 엔티티의 식별자 값을 키로 사용하여 해당 엔티티의 이전 상태를 저장

### 요구사항3 - 더티체킹 구현

1. 엔티티를 find 시 스냅샷을 만들어 둔 후,
2. 엔티티를 save 할 때, 스냅샷과 비교하여 변경 내용을 감지
3. CustomJpaRepository 에서 id가 있는 경우 merge, 없는 경우 persist
4. merge 시 변경을 감지하여, 변경된 경우 update
