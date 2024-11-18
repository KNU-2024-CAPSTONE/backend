//package knu.project.crm;
//
//public List<PurchaseLogDto> getPurchaseLogByMemberId(Long memberId) {
//    String url = BASE_URL + "/purchase-log/" + memberId;
//    ResponseEntity<PurchaseLogDto[]> response = restTemplate.getForEntity(url, PurchaseLogDto[].class);
//    return List.of(response.getBody());
//}
