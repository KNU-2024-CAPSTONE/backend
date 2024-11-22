package knu.project.crm.controller;

import knu.project.crm.service.RecommendService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("shop/{shopId}/recommend")
public class RecommendController {
    private final RecommendService recommendService;

    public RecommendController(RecommendService recommendService) {
        this.recommendService = recommendService;
    }

    @GetMapping("/popular-categories")
    public Map<String, Map<String, Long>> getAllPopularCategories(@PathVariable Integer shopId) {
        // Define age groups and genders
        List<int[]> ageGroups = List.of(
                new int[]{10, 19},
                new int[]{20, 29},
                new int[]{30, 39},
                new int[]{40, 49},
                new int[]{50, 59},
                new int[]{60, 69}
        );
        List<String> genders = List.of("male", "female");

        // Use LinkedHashMap to maintain insertion order
        Map<String, Map<String, Long>> result = new LinkedHashMap<>();

        for (int[] ageGroup : ageGroups) {
            for (String gender : genders) {
                String key = ageGroup[0] + "대 " + (gender.equals("male") ? "남자" : "여자");
                Map<String, Long> popularCategories = recommendService.getPopularCategoriesByGenderAndAge(
                        shopId, gender, ageGroup[0], ageGroup[1]);
                result.put(key, popularCategories);
            }
        }

        return result;
    }
    //@GetMapping("/performance")


}
