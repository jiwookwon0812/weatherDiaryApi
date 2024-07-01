package zerobase.weather.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import zerobase.weather.domain.Diary;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;

    @Operation(summary = "일기 테스트와 날씨를 이용해서 DB에 일기 저장")
    @PostMapping("/create/diary")
    void createDiary(@RequestParam
                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                     @Parameter(description = "날짜 형식 : yyyy-MM-dd", example = "2020-02-02") LocalDate date,
                     @RequestBody String text) {
        diaryService.createDiary(date, text);
    }

    @Operation(summary = "선택할 날짜의 모든 일기 데이터를 가져옵니다")
    @GetMapping("/read/diary")
    List<Diary> readDiary(@RequestParam
                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                          @Parameter(description = "날짜 형식 : yyyy-MM-dd", example = "2020-02-02") LocalDate date) {
        return diaryService.readDiary(date);
    }

    @Operation(summary = "선택한 기간중의 모든 일기 데이터를 가져옵니다")
    @GetMapping("/read/diaries")
    List<Diary> readDiaries(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Parameter(description = "조회할 기간의 첫번째날", example = "2020-02-02") LocalDate startDate,
                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Parameter(description = "조회할 기간의 마지막날", example = "2020-02-02") LocalDate endDate) {
        return diaryService.readDiaries(startDate, endDate);
    }

    @Operation(summary = "선택한 날짜의 일기를 새 일기로 업데이트 해줍니다")
    @PutMapping("/update/diary")
    void updateDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                     @Parameter(description = "날짜 형식 : yyyy-MM-dd", example = "2020-02-02") LocalDate date,
                     @RequestBody String text) {
        diaryService.updateDiary(date, text);
    }

    @Operation(summary = "선택한 날짜의 모든 일기 데이터를 삭제합니다")
    @DeleteMapping("delete/diary")
    void deleteDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                     @Parameter(description = "삭제할 날짜", example = "2020-02-02") LocalDate date) {
        diaryService.deleteDiary(date);
    }

}
