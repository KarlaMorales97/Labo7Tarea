package com.morales.labo7t;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.morales.labo7t.Datos.estudiante;
import com.morales.labo7t.Entidades.DBHelper;

import static java.lang.Float.parseFloat;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link modificar.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link modificar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class modificar extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    EditText carnet,nombre,nota;
    private Button btnBuscar,btnEliminar,btnActualizar,btnLimpiar;
    private boolean flag=false;

    private OnFragmentInteractionListener mListener;

    public modificar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment modificar.
     */
    // TODO: Rename and change types and number of parameters
    public static modificar newInstance(String param1, String param2) {
        modificar fragment = new modificar();
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
        View view = inflater.inflate(R.layout.fragment_modificar, container, false);

       // carnet =(EditText) view.findViewById(R.id);
        carnet = (EditText) view.findViewById(R.id.carnetModificar);
        nombre = (EditText)view.findViewById(R.id.nombreModificarE);
        nota = (EditText)view.findViewById(R.id.notaEditTextM);
        btnBuscar = (Button)view.findViewById(R.id.btnBuscar);
        btnActualizar = (Button)view.findViewById(R.id.btnActualizarM);
        btnEliminar = (Button)view.findViewById(R.id.btnEliminarM);
        btnLimpiar = (Button)view.findViewById(R.id.btnLimpiarM);



        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estudiante P = DBHelper.myDB.findUser(carnet.getText().toString());
                if(P==null){
                    Toast.makeText(getContext(),"El usuario no fue encontrado", Toast.LENGTH_SHORT).show();
                    limpiar();
                }


                else{
                    nombre.setText(P.getNombre());
                    nota.setText(P.getNota());
                    flag=true;
                }
            }
        });


        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag) {
                    Toast.makeText(getContext(), "El usuario no existe", Toast.LENGTH_SHORT).show();
                } else {
                    if (parseFloat(nota.getText().toString()) > 100) {
                        Toast.makeText(getContext(), "Verificar nota", Toast.LENGTH_SHORT).show();
                        limpiar();
                        flag = false;
                    } else {
                        DBHelper.myDB.editUser(new estudiante(carnet.getText().toString(), nombre.getText().toString(), nota.getText().toString()));
                        flag=false;
                    }
                }
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper.myDB.deleteUser(carnet.getText().toString());
                limpiar();
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiar();
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

    public void limpiar(){
        nombre.setText("");
        carnet.setText("");
        nota.setText("");
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
