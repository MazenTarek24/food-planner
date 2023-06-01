package com.example.foodplanner.allmeal.view;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodplanner.R;
import com.example.foodplanner.db.MealDatabase;
import com.example.foodplanner.model.Meal;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;


public class DetailsFragment extends Fragment {

    Button btn_add;
    int mSelectedIndex;
    Meal meal;

    public DetailsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_details, container, false);

        if (getArguments() != null) {
             meal = getArguments().getParcelable("meal");
            if (meal != null) {
                ImageView mealImageView = view.findViewById(R.id.str_thumb);
                Picasso.get().load(meal.getStrMealThumb()).into(mealImageView);

                TextView mealNameTextView = view.findViewById(R.id.str_meal);
                mealNameTextView.setText(meal.getStrMeal());

                TextView mealAreaTextView = view.findViewById(R.id.txt_area);
                mealAreaTextView.setText(meal.getStrArea());

                TextView mealDescTextView = view.findViewById(R.id.desc_meal);
                mealDescTextView.setText(meal.getStrInstructions());

               YouTubePlayerView youTubePlayerView = view.findViewById(R.id.youtube_player_view);
                getLifecycle().addObserver(youTubePlayerView);

              youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = extractVideoIdFromUrl(meal.getStrYoutube());
                        youTubePlayer.loadVideo(videoId, 0);
                    }
                });


                TextView ing1 = view.findViewById(R.id.ing1);
                if (ing1 !=null && meal.getStrIngredient1() != null && !meal.getStrIngredient1().isEmpty()) {
                    ing1.setText(meal.getStrIngredient1());
                } else {
                    ing1.setVisibility(View.GONE);
                }

                TextView ing2 = view.findViewById(R.id.ing2);
                if (ing2 !=null &&meal.getStrIngredient2() != null && !meal.getStrIngredient2().isEmpty()) {
                    ing2.setText(meal.getStrIngredient2());
                } else {
                    ing2.setVisibility(View.GONE);
                }

                TextView ing3 = view.findViewById(R.id.ing3);
                if (ing3 !=null &&meal.getStrIngredient3() != null && !meal.getStrIngredient3().isEmpty()) {
                    ing3.setText(meal.getStrIngredient3());
                } else {
                    ing3.setVisibility(View.GONE);
                }

                TextView ing4 = view.findViewById(R.id.ing4);
                if (ing4 !=null&& meal.getStrIngredient4() != null && !meal.getStrIngredient4().isEmpty()) {
                    ing4.setText(meal.getStrIngredient4());
                } else {
                    ing4.setVisibility(View.GONE);
                }

                TextView ing5 = view.findViewById(R.id.ing5);
                if (ing5 !=null &&meal.getStrIngredient5() != null && !meal.getStrIngredient5().isEmpty()) {
                    ing5.setText(meal.getStrIngredient5());
                } else {
                    ing5.setVisibility(View.GONE);
                }

                TextView ing6 = view.findViewById(R.id.ing6);
                if (ing6 !=null &&meal.getStrIngredient6() != null && !meal.getStrIngredient6().isEmpty()) {
                    ing6.setText(meal.getStrIngredient6());
                } else {
                    ing6.setVisibility(View.GONE);
                }

                TextView ing7 = view.findViewById(R.id.ing7);
                if (ing7 !=null &&meal.getStrIngredient7() != null && !meal.getStrIngredient7().isEmpty()) {
                    ing7.setText(meal.getStrIngredient7());
                } else {
                    ing7.setVisibility(View.GONE);
                }

                TextView ing8 = view.findViewById(R.id.ing8);
                if (ing8 !=null &&meal.getStrIngredient8() != null && !meal.getStrIngredient8().isEmpty()) {
                    ing8.setText(meal.getStrIngredient8());
                } else {
                    ing8.setVisibility(View.GONE);
                }

                TextView ing9 = view.findViewById(R.id.ing9);
                if (ing9 !=null &&meal.getStrIngredient9() != null && !meal.getStrIngredient9().isEmpty()) {
                    ing9.setText(meal.getStrIngredient9());
                } else {
                    ing9.setVisibility(View.GONE);
                }

                TextView ing10 = view.findViewById(R.id.ing10);
                if (ing10 !=null &&meal.getStrIngredient10() != null && !meal.getStrIngredient10().isEmpty()) {
                    ing10.setText(meal.getStrIngredient10());
                } else {
                    ing10.setVisibility(View.GONE);
                }

                TextView ing11 = view.findViewById(R.id.ing11);
                if (ing11 !=null &&meal.getStrIngredient11() != null && !meal.getStrIngredient11().isEmpty()) {
                    ing11.setText(meal.getStrIngredient11());
                } else {
                    ing11.setVisibility(View.GONE);
                }

                TextView ing12 = view.findViewById(R.id.ing12);
                if (ing12 !=null &&meal.getStrIngredient12() != null && !meal.getStrIngredient12().isEmpty()) {
                    ing12.setText(meal.getStrIngredient12());
                } else {
                    ing12.setVisibility(View.GONE);
                }

                TextView ing13 = view.findViewById(R.id.ing13);
                if (ing13!=null &&meal.getStrIngredient13() != null && !meal.getStrIngredient13().isEmpty()) {
                    ing13.setText(meal.getStrIngredient13());
                } else {
                    ing13.setVisibility(View.GONE);
                }

                TextView ing14 = view.findViewById(R.id.ing14);
                if (ing14!=null &&meal.getStrIngredient14() != null && !meal.getStrIngredient14().isEmpty()) {
                    ing14.setText(meal.getStrIngredient14());
                } else {
                    ing14.setVisibility(View.GONE);
                }

                TextView ing15 = view.findViewById(R.id.ing15);
                if (ing15 !=null && meal.getStrIngredient15() != null && !meal.getStrIngredient15().isEmpty()) {
                    ing15.setText(meal.getStrIngredient15());
                } else {
                    ing15.setVisibility(View.GONE);
                }

                TextView ing16 = view.findViewById(R.id.ing16);
                if (ing16 !=null &&meal.getStrIngredient16() != null && !meal.getStrIngredient16().isEmpty()) {
                    ing16.setText(meal.getStrIngredient16());
                } else {
                    ing16.setVisibility(View.GONE);
                }

                TextView ing17 = view.findViewById(R.id.ing17);
                if (ing17 !=null  && meal.getStrIngredient17() != null && !meal.getStrIngredient17().isEmpty()) {
                    ing17.setText(meal.getStrIngredient17());
                } else {
                    ing17.setVisibility(View.GONE);
                }

                TextView ing18 = view.findViewById(R.id.ing18);
                if (ing18 !=null  && meal.getStrIngredient18() != null && !meal.getStrIngredient18().isEmpty()) {
                    ing18.setText(meal.getStrIngredient18());
                } else {
                    ing18.setVisibility(View.GONE);
                }

                TextView ing19 = view.findViewById(R.id.ing19);
                if (ing19 !=null &&meal.getStrIngredient19() != null && !meal.getStrIngredient19().isEmpty()) {
                    ing19.setText(meal.getStrIngredient19());
                } else {
                    ing19.setVisibility(View.GONE);
                }

                TextView ing20 = view.findViewById(R.id.ing20);
                if (ing20 !=null &&meal.getStrIngredient20() != null && !meal.getStrIngredient20().isEmpty()) {
                    ing20.setText(meal.getStrIngredient20());
                } else {
                    ing20.setVisibility(View.GONE);
                }

                TextView mes1 = view.findViewById(R.id.meas1);
                if (mes1 !=null &&meal.getStrMeasure1() != null && !meal.getStrMeasure1().isEmpty()) {
                    mes1.setText(meal.getStrMeasure1());
                } else {
                    mes1.setVisibility(View.GONE);
                }

                TextView mes2 = view.findViewById(R.id.meas2);
                if (mes2 !=null && meal.getStrMeasure2() != null && !meal.getStrMeasure2().isEmpty()) {
                    mes2.setText(meal.getStrMeasure2());
                } else {
                    mes2.setVisibility(View.GONE);
                }

                TextView mes3 = view.findViewById(R.id.meas3);
                if (mes3 !=null && meal.getStrMeasure3() != null && !meal.getStrMeasure3().isEmpty()) {
                    mes3.setText(meal.getStrMeasure3());
                } else {
                    mes3.setVisibility(View.GONE);
                }

// Repeat the above pattern for the remaining TextViews (mes4 to mes20)
                TextView mes4 = view.findViewById(R.id.meas4);
                if (mes4 !=null && meal.getStrMeasure4() != null && !meal.getStrMeasure4().isEmpty()) {
                    mes4.setText(meal.getStrMeasure4());
                } else {
                    mes4.setVisibility(View.GONE);
                }

                TextView mes5 = view.findViewById(R.id.meas5);
                if (mes5 !=null && meal.getStrMeasure5() != null && !meal.getStrMeasure5().isEmpty()) {
                    mes5.setText(meal.getStrMeasure5());
                } else {
                    mes5.setVisibility(View.GONE);
                }

// Repeat the above pattern for the remaining TextViews (mes6 to mes20)
                TextView mes6 = view.findViewById(R.id.meas6);
                if (mes6 !=null && meal.getStrMeasure6() != null && !meal.getStrMeasure6().isEmpty()) {
                    mes6.setText(meal.getStrMeasure6());
                } else {
                    mes6.setVisibility(View.GONE);
                }

                TextView mes7 = view.findViewById(R.id.meas7);
                if (mes7 !=null && meal.getStrMeasure7() != null && !meal.getStrMeasure7().isEmpty()) {
                    mes7.setText(meal.getStrMeasure7());
                } else {
                    mes7.setVisibility(View.GONE);
                }

                TextView mes8 = view.findViewById(R.id.meas8);
                if (mes8 !=null && meal.getStrMeasure8() != null && !meal.getStrMeasure8().isEmpty()) {
                    mes8.setText(meal.getStrMeasure8());
                } else {
                    mes8.setVisibility(View.GONE);
                }

                TextView mes9 = view.findViewById(R.id.meas9);
                if (mes9 !=null && meal.getStrMeasure9() != null && !meal.getStrMeasure9().isEmpty()) {
                    mes9.setText(meal.getStrMeasure9());
                } else {
                    mes9.setVisibility(View.GONE);
                }

                TextView mes10 = view.findViewById(R.id.meas10);
                if (mes10 !=null && meal.getStrMeasure10() != null && !meal.getStrMeasure10().isEmpty()) {
                    mes10.setText(meal.getStrMeasure10());
                } else {
                    mes10.setVisibility(View.GONE);
                }

                TextView mes11 = view.findViewById(R.id.meas11);
                if (mes11 !=null && meal.getStrMeasure11() != null && !meal.getStrMeasure11().isEmpty()) {
                    mes11.setText(meal.getStrMeasure11());
                } else {
                    mes11.setVisibility(View.GONE);
                }

                TextView mes12 = view.findViewById(R.id.meas12);
                if (mes12 !=null && meal.getStrMeasure12() != null && !meal.getStrMeasure12().isEmpty()) {
                    mes12.setText(meal.getStrMeasure12());
                } else {
                    mes12.setVisibility(View.GONE);
                }

                TextView mes13 = view.findViewById(R.id.meas13);
                if (mes13 !=null && meal.getStrMeasure13() != null && !meal.getStrMeasure13().isEmpty()) {
                    mes13.setText(meal.getStrMeasure13());
                } else {
                    mes13.setVisibility(View.GONE);
                }

                TextView mes14 = view.findViewById(R.id.meas14);
                if (mes14 !=null && meal.getStrMeasure14() != null && !meal.getStrMeasure14().isEmpty()) {
                    mes14.setText(meal.getStrMeasure14());
                } else {
                    mes14.setVisibility(View.GONE);
                }

                TextView mes15 = view.findViewById(R.id.meas15);
                if (mes15 !=null && meal.getStrMeasure15() != null && !meal.getStrMeasure15().isEmpty()) {
                    mes15.setText(meal.getStrMeasure15());
                } else {
                    mes15.setVisibility(View.GONE);
                }

                TextView mes16 = view.findViewById(R.id.meas16);
                if (mes16 !=null && meal.getStrMeasure16() != null && !meal.getStrMeasure16().isEmpty()) {
                    mes16.setText(meal.getStrMeasure16());
                } else {
                    mes16.setVisibility(View.GONE);
                }

                TextView mes17 = view.findViewById(R.id.meas17);
                if (mes17 !=null && meal.getStrMeasure17() != null && !meal.getStrMeasure17().isEmpty()) {
                    mes17.setText(meal.getStrMeasure17());
                } else {
                    mes17.setVisibility(View.GONE);
                }

                TextView mes18 = view.findViewById(R.id.meas18);
                if (mes18 !=null && meal.getStrMeasure18() != null && !meal.getStrMeasure18().isEmpty()) {
                    mes18.setText(meal.getStrMeasure18());
                } else {
                    mes18.setVisibility(View.GONE);
                }

                TextView mes19 = view.findViewById(R.id.meas19);
                if (mes19 !=null && meal.getStrMeasure19() != null && !meal.getStrMeasure19().isEmpty()) {
                    mes19.setText(meal.getStrMeasure19());
                } else {
                    mes19.setVisibility(View.GONE);
                }

                TextView mes20 = view.findViewById(R.id.meas20);
                if (mes20 !=null && meal.getStrMeasure20() != null && !meal.getStrMeasure20().isEmpty()) {
                    mes20.setText(meal.getStrMeasure20());
                } else {
                    mes20.setVisibility(View.GONE);
                }
            }
        }
        return view;
    }

    private String extractVideoIdFromUrl(String url) {
        String videoId = "";
        if (url != null && url.trim().length() > 0) {
            String[] splitUrl = url.split("v=");
            if (splitUrl.length > 1) {
                videoId = splitUrl[1];
            } else {
                videoId = url;
            }
        }
        return videoId;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_add = view.findViewById(R.id.btn_add_plan);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    private void showDialog() {
        List<String> daysOfWeek = Arrays.asList("SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_single_choice, daysOfWeek);

        new AlertDialog.Builder(getContext())
                .setTitle("Select a day of the week")
                .setSingleChoiceItems(adapter, 0, (dialog, which) -> mSelectedIndex = which)
                .setPositiveButton("OK", (dialog, which) -> {
                    if (mSelectedIndex >= 0) {
                String selectedDay = daysOfWeek.get(mSelectedIndex);
                MealDatabase.getInstance(getContext()).getMealsDao().insertMeal(meal);
                MealDatabase.getInstance(getContext()).getMealsDao().
                        updateColumnDay(meal.getIdMeal(), selectedDay.toLowerCase());
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}