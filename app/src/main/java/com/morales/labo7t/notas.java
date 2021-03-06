package com.morales.labo7t;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.morales.labo7t.Datos.estudiante;
import com.morales.labo7t.Entidades.DBHelper;

import java.util.ArrayList;

import static com.morales.labo7t.Entidades.DBHelper.myDB;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link notas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class notas extends Fragment implements agregar.OnFragmentInteractionListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Fragment activity = notas.this;
    notas context = notas.this;
    RecyclerView recyclerView;
    TextView carnetTXT, nombreTXT;
    ArrayList<estudiante> listBeneficiary;
    RecyclerAdapter recyclerAdapter;
    private DBHelper databaseHelper;
    TextView promedio;
    private ArrayList<estudiante> estudiantes;

    private OnFragmentInteractionListener mListener;

    public notas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment notas.
     */
    // TODO: Rename and change types and number of parameters
    public static notas newInstance(String param1, String param2) {
        notas fragment = new notas();
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
        View view = inflater.inflate(R.layout.fragment_notas, container, false);

        promedio= view.findViewById(R.id.Promedio);
        String texto= ""+DBHelper.myDB.promedio();
        promedio.setText(texto);


        recyclerView = view.findViewById(R.id.recycler);
        listBeneficiary = new ArrayList<>();
        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        //myDB = new DBHelper(getContext());
        listBeneficiary = DBHelper.myDB.getEstudiantesDBH();
        recyclerAdapter = new RecyclerAdapter(listBeneficiary);
        recyclerView.setAdapter(recyclerAdapter);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        String ans= ""+DBHelper.myDB.promedio();
        promedio.setText(ans);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
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


    /**
     * This method is to initialize views
     */

    /**
     * This method is to initialize objects to be used
     */
  /*  private void initObjects() {
        listBeneficiary = new ArrayList<>();
        recyclerAdapter = new RecyclerAdapter(listBeneficiary);
        recyclerView = new RecyclerView(getContext());

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(recyclerAdapter);

    }*/




}
