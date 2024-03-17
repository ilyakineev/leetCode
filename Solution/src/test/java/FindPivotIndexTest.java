import Solution.FindPivotIndex;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FindPivotIndexTest {

    private static FindPivotIndex pivotIndex;

    @BeforeAll
    static void beforeAll() {
        pivotIndex = new FindPivotIndex();
    }

    @Test
    public void testPivotIndex_EmptyArray() {
        // Пустой массив
        int[] nums = {};
        assertEquals(-1, pivotIndex.pivotIndex(nums));
    }

    @Test
    public void testPivotIndex_SingleElementArray() {
        // Массив с одним элементом
        int[] nums = {5};
        assertEquals(0, pivotIndex.pivotIndex(nums));
    }

    @Test
    public void testPivotIndex_AllNegativeNumbers() {
        // Массив только с отрицательными числами
        int[] nums = {-3, -6, -1, -4, -2};
        assertEquals(2, pivotIndex.pivotIndex(nums));
    }

    @Test
    public void testPivotIndex_AllPositiveNumbers() {
        // Массив только с положительными числами
        int[] nums = {3, 6, 1, 4, 2};
        assertEquals(-1, pivotIndex.pivotIndex(nums));
    }

    @Test
    public void testPivotIndex_MixedNumbers() {
        // Массив с отрицательными и положительными числами
        int[] nums = {-1, 2, -3, 4, -5, 6};
        assertEquals(3, pivotIndex.pivotIndex(nums));
    }

    @Test
    public void testPivotIndex_MaxMinValues() {
        // Массив с максимальными и минимальными значениями
        int[] nums = {1000, -1000, 500, -500, 250, -250};
        assertEquals(2, pivotIndex.pivotIndex(nums));
    }

    @RepeatedTest(10)
    public void testPivotIndexPerformance() {
        int[] nums = generateLargeArray(1000, -1000, 1000); // Уменьшение размера массива
        long startTime = System.nanoTime(); // Замер времени начала выполнения

        int result = pivotIndex.pivotIndex(nums); // Выполнение метода
        // Не выводим результат на консоль
        long endTime = System.nanoTime(); // Замер времени окончания выполнения
        long duration = (endTime - startTime) / 1000000; // Расчет длительности выполнения в миллисекундах
        // Не выводим длительность выполнения на консоль
    }

    @Test
    public void testMemoryUsage() {
        int[] array = generateLargeArray(1000, -1000, 1000); // Уменьшение размера массива

        // Получаем информацию о памяти JVM
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        long memoryBefore = memoryBean.getHeapMemoryUsage().getUsed();

        // Создаем большой массив
        int[] nums = generateLargeArray(1000, -1000, 1000); // Уменьшение размера массива

        long memoryAfter = memoryBean.getHeapMemoryUsage().getUsed();

        // Определяем объем памяти, использованный для создания массива
        long memoryUsed = memoryAfter - memoryBefore;

        // Выводим информацию о потреблении памяти
        System.out.println("Memory used: " + memoryUsed + " bytes");

        // Проверяем, что память, использованная для создания массива, в пределах ожидаемого
        assertTrue(memoryUsed < 1048576); // 1 MB
    }

    public static int[] generateLargeArray(int size, int minValue, int maxValue) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер массива должен быть положительным числом");
        }
        int[] array = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt((maxValue - minValue) + 1) + minValue;
        }
        return array;
    }
}