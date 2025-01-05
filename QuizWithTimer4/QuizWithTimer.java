import java.util.*;

class Question
 {
    private String questionText;
    private String[] options;
    private int correctAnswerIndex;

    public Question(String questionText, String[] options, int correctAnswerIndex)
     {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() 
    {
        return questionText;
    }

    public String[] getOptions() 
    {
        return options;
    }

    public int getCorrectAnswerIndex()
     {
        return correctAnswerIndex;
    }
}

class QuizApplication
 {
    private List<Question> questions;
    private int score;

    public QuizApplication()
     {
        questions = new ArrayList<>();
        score = 0;
    }

    public void addQuestion(Question question) 
    {
        questions.add(question);
    }

    public void startQuiz() 
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Quiz!");
        System.out.println("You have 10 seconds to answer each question. Good luck!");

        for (int i = 0; i < questions.size(); i++) 
        {
            Question question = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + question.getQuestionText());
            String[] options = question.getOptions();

            for (int j = 0; j < options.length; j++) 
            {
                System.out.println((j + 1) + ". " + options[j]);
            }

            Timer timer = new Timer();
            TimerTask task = new TimerTask()
             {
                @Override
                public void run() {
                    System.out.println("\nTime's up! Moving to the next question.");
                    System.exit(0);
                }
            };
            timer.schedule(task, 10000);

            System.out.print("Enter your answer (1-4): ");
            int userAnswer = scanner.nextInt();

            timer.cancel();

            if (userAnswer - 1 == question.getCorrectAnswerIndex())
             {
                System.out.println("Correct!");
                score++;
            } else
             {
                System.out.println("Incorrect! The correct answer was: " +
                        options[question.getCorrectAnswerIndex()]);
            }
        }

        System.out.println("\nQuiz Over!");
        System.out.println("Your final score is: " + score + "/" + questions.size());
        scanner.close();
    }
}

public class QuizWithTimer {
    public static void main(String[] args) {
        QuizApplication quiz = new QuizApplication();
        quiz.addQuestion(new Question("What is the capital of France?",
                new String[]{"Paris", "London", "Berlin", "Madrid"}, 0));
        quiz.addQuestion(new Question("Which programming language is known as platform-independent?",
                new String[]{"Python", "Java", "C++", "JavaScript"}, 1));
        quiz.addQuestion(new Question("What is the largest planet in our solar system?",
                new String[]{"Earth", "Venus", "Mars", "Jupiter"}, 3));
        quiz.addQuestion(new Question("What is 5 + 3?",
                new String[]{"5", "8", "7", "9"}, 1));
        quiz.startQuiz();
    }
}