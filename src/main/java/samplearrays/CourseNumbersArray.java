package samplearrays;

public class CourseNumbersArray {
    public static void main(String[] args) {
        int[] registeredCourses = {1010, 1020, 2080, 2140, 2150, 2160};
        registeredCourses = addCourse(registeredCourses, 2060);
        printCourses(registeredCourses);
        System.out.println(checkCourse(registeredCourses, 2060));
    }
    public static int[] addCourse(int[] registeredCourses, int newCourse){
        int[] updatedCourses = new int[registeredCourses.length + 1];
        for (int i = 0; i < registeredCourses.length; i++){
            updatedCourses[i] = registeredCourses[i];
        }
        updatedCourses[updatedCourses.length - 1] = newCourse;
        return updatedCourses;
    }
    public static void printCourses(int[] registeredCourses) {
        System.out.println("---------Courses----------");
        for (int course : registeredCourses){
            System.out.println(course);
        }
        System.out.println("--------------------------");
    }
    public static boolean checkCourse(int[] registeredCourses, int course){
        for(int registeredCourse : registeredCourses){
            if (registeredCourse == course) return true;
        }
        return false;
    }
}
