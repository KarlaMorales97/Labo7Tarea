package com.morales.labo7t;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.morales.labo7t.Datos.estudiante;
import com.morales.labo7t.Entidades.DBHelper;

import static com.morales.labo7t.Entidades.DBHelper.*;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link agregar.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link agregar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class agregar extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private EditText txtCarnet, txtNombre, editNota;
    private Button btnAgregar;
    private boolean flag = false;


    // TODO: Rename and change types of parameters



    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public agregar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment agregar.
     */
    // TODO: Rename and change types and number of parameters
    public static agregar newInstance(String param1, String param2) {
        agregar fragment = new agregar();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_agregar, container, false);
        btnAgregar = (Button) view.findViewById(R.id.btnAgregar);
        txtCarnet = (EditText) view.findViewById(R.id.carnetAgregarEdit);
        txtNombre = (EditText) view.findViewById(R.id.nombreAgregrar);
        editNota = (EditText)view.findViewById(R.id.notaAgregar);


        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                myDB = new DBHelper(getContext());

                flag = DBHelper.myDB.add(new estudiante(
                        txtCarnet.getText().toString(), txtNombre.getText().toString(), editNota.getText().toString()));

                if(flag){
                    Log.d("Edit", txtCarnet.getText().toString());
                }
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        return view;

    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



}
